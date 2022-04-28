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

import cn.edu.tjnu.tutor.common.annotation.Log;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * 操作日志服务层。
 *
 * @author 王帅
 * @since 2.0
 */
public interface OperLogService {

    /**
     * 记录操作日志信息。
     *
     * @param request 当前请求
     * @param userDetails 用户信息
     * @param log {@link Log} 注解
     * @param method 方法名称
     * @param paramsArray 方法参数
     * @param e 异常信息
     * @param jsonResult 返回结果
     */
    void recordOperLog(HttpServletRequest request, UserDetails userDetails,
                       Log log, String method, Object[] paramsArray,
                       Exception e, Object jsonResult);

}