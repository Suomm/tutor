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

package cn.edu.tjnu.tutor.common.core.service;

import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 令牌 服务层。
 *
 * @author 王帅
 * @since 2.0
 */
public interface TokenService {

    /**
     * 获取登录用户。
     *
     * @param request 请求
     * @return {@link LoginUser}
     */
    LoginUser getLoginUser(HttpServletRequest request);

    /**
     * 验证令牌。
     *
     * @param loginUser 登录用户
     */
    void verifyToken(LoginUser loginUser);

}