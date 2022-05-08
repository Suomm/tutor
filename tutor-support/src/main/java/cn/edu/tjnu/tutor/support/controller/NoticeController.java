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

import cn.edu.tjnu.tutor.common.annotation.Log;
import cn.edu.tjnu.tutor.common.core.controller.BaseController;
import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.core.domain.dto.PageDTO;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import cn.edu.tjnu.tutor.system.domain.entity.Notice;
import cn.edu.tjnu.tutor.system.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.enums.Category.NOTICE;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 公告信息控制层。
 *
 * @author 王帅
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    private final NoticeService noticeService;

    /**
     * 分页查询公告信息。
     *
     * @param pageDTO 分页参数
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<PageVO<Notice>> list(@Validated PageDTO pageDTO) {
        return pageSuccess(noticeService.page(pageDTO.page()));
    }

    /**
     * 根据公告主键获取详细信息。
     *
     * @param noticeId 公告主键
     * @return 公告信息详情
     */
    @GetMapping("getInfo/{noticeId}")
    public AjaxResult<Notice> getInfo(@PathVariable Integer noticeId) {
        return success(noticeService.getById(noticeId));
    }

    /**
     * 添加公告信息。
     *
     * @param notice 公告信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Log(category = NOTICE, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) Notice notice) {
        return toResult(noticeService.save(notice));
    }

    /**
     * 更新公告信息。
     *
     * @param notice 公告信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @PutMapping("update")
    @Log(category = NOTICE, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) Notice notice) {
        return toResult(noticeService.updateById(notice));
    }

    /**
     * 根据公告主键删除公告信息。
     *
     * @param noticeId 公告主键
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @DeleteMapping("remove/{noticeId}")
    @Log(category = NOTICE, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer noticeId) {
        return toResult(noticeService.removeById(noticeId));
    }

}