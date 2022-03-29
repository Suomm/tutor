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

package cn.edu.tjnu.tutor.system.service;

import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.system.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户信息服务层。
 *
 * @author 王帅
 * @since 1.0
 */
public interface UserService extends IService<User> {

    /**
     * 学生学号的正则匹配表达式。
     */
    String RE_STUDENT_CODE = "\\d{10}";

    /**
     * 教师工号的正则匹配表达式。
     */
    String RE_TEACHER_CODE = "\\d{6}";

    /**
     * 用户登录并获取相应权限信息，如果用户不存在则注册用户。
     *
     * @param user 用户实体
     * @return 登录用户
     */
    LoginUser login(User user);

}