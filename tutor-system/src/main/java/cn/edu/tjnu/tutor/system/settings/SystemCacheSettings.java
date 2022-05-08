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

package cn.edu.tjnu.tutor.system.settings;

import cn.edu.tjnu.tutor.system.service.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 系统缓存初始化设置。
 *
 * @author 王帅
 * @since 2.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SystemCacheSettings implements ApplicationRunner {

    private final ConfigService configService;

    @Override
    public void run(ApplicationArguments args) {
        configService.loadConfigCache();
        log.info("已成功加载配置参数缓存");
    }

}