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

import cn.edu.tjnu.tutor.common.enums.UserStatus;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录信息服务层。
 *
 * @author 王帅
 * @since 2.0
 */
public interface LoginInfoService {

    /**
     * 记录用户（登录/注销）状态。
     *
     * @param request     请求对象
     * @param userDetails 用户信息
     * @param status      用户状态
     */
    void recordLoginInfo(HttpServletRequest request, UserDetails userDetails, UserStatus status);

}