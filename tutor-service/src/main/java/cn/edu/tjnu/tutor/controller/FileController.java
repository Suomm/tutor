/*
 * Copyright (C) 2021-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.edu.tjnu.tutor.controller;

import cn.edu.tjnu.tutor.component.MinioTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 文件操作控制层。
 *
 * @author 王帅
 * @since 1.0
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private MinioTemplate minioTemplate;

    /**
     * （任何人）上传图片保存在 Minio 中。
     */
    @PostMapping("upload")
    public String upload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename == null) {
            filename = UUID.randomUUID().toString();
        }
        return minioTemplate.upload(filename, file);
    }

}
