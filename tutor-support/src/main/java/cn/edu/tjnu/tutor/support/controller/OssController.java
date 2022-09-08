/*
 * Copyright 2021-2022 the original author and authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.edu.tjnu.tutor.support.controller;

import cn.edu.tjnu.tutor.common.core.controller.BaseController;
import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.enums.ExceptionType;
import cn.edu.tjnu.tutor.common.plugin.MinioPlugin;
import cn.edu.tjnu.tutor.system.domain.entity.Oss;
import cn.edu.tjnu.tutor.system.service.OssService;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/oss")
@ConditionalOnBean(MinioPlugin.class)
public class OssController extends BaseController {

    private final OssService ossService;
    private final MinioPlugin minioPlugin;

    /**
     * 上传文件。
     *
     * @param file 文件
     * @return 可查看文件的 URL 地址
     */
    @PostMapping("upload")
    public AjaxResult<String> upload(MultipartFile file) {
        String filename = IdUtil.fastSimpleUUID();
        String url = minioPlugin.upload(filename, file);
        // 初始化 Oss 文件上传记录对象
        Oss oss = new Oss();
        oss.setUrl(url);
        oss.setFileName(filename);
        String originalName = file.getOriginalFilename();
        if (originalName != null) {
            int begin = originalName.lastIndexOf('.');
            oss.setFileSuffix(originalName.substring(begin));
            oss.setOriginalName(originalName);
        }
        // 保存文件上传记录到数据库
        ossService.save(oss);
        return success(url);
    }

    /**
     * 删除文件。
     *
     * @param filename 文件名称
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{filename}")
    public AjaxResult<Void> remove(@PathVariable String filename) {
        if (!ossService.lambdaQuery()
                .eq(Oss::getFileName, filename)
                .exists()) {
            return error(ExceptionType.FILE_NOT_FOUND, filename);
        }
        // 删除文件并删除数据库记录
        minioPlugin.remove(filename);
        return toResult(ossService.lambdaUpdate()
                .eq(Oss::getFileName, filename)
                .remove());
    }

}