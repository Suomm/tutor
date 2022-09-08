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

package cn.edu.tjnu.tutor.common.plugin;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.text.CharSequenceUtil;
import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

/**
 * Minio 插件。
 *
 * @author 王帅
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(MinioProperties.class)
@ConditionalOnProperty(prefix = "minio", name = "enable", havingValue = "true")
public class MinioPlugin implements InitializingBean {

    private final MinioProperties minioProperties;

    // 根据配置信息初始化 MinioClient

    private MinioClient minioClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        minioClient = MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(minioProperties.getBucket()).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(minioProperties.getBucket()).build());
            ClassPathResource resource = new ClassPathResource("bucket-policy.json");
            String config = String.format(resource.readStr(StandardCharsets.UTF_8),
                    minioProperties.getBucket(), minioProperties.getBucket());
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .config(config)
                    .build());
        }
    }

    /**
     * 保存文件到 Minio 中。
     *
     * @param filename 文件名称
     * @param file     文件内容
     * @return 可以访问的地址
     */
    public String upload(String filename, MultipartFile file) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .bucket(minioProperties.getBucket())
                    .contentType(file.getContentType())
                    .object(filename)
                    .build());
            return minioProperties.getBaseUrl().concat(filename);
        } catch (Exception e) {
            return CharSequenceUtil.EMPTY;
        }
    }

    /**
     * 删除 Minio 中保存的文件。
     *
     * @param filename 文件名称
     */
    public void remove(String filename) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .object(filename)
                    .build());
        } catch (Exception e) {
            // ignored.
        }
    }

}
