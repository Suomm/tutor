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

package cn.edu.tjnu.tutor.admin;

import cn.edu.tjnu.tutor.common.annotation.EnableCustomConfig;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Tutor Admin 主启动类。
 *
 * @author 王帅
 * @since 2.0
 */
@EnableAdminServer
@EnableCustomConfig
@SpringBootApplication
public class TutorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorAdminApplication.class, args);
    }

}
