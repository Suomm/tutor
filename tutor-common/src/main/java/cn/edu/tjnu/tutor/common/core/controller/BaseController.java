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

package cn.edu.tjnu.tutor.common.core.controller;

import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.common.util.SecurityUtils;

/**
 * 基本控制器。
 *
 * @author 王帅
 * @since 2.0
 */
public class BaseController {

    /**
     * 返回成功。
     */
    public AjaxResult<Void> success() {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息。
     */
    public AjaxResult<Void> error() {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息。
     */
    public AjaxResult<Void> success(String message) {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息。
     */
    public AjaxResult<Void> error(String message) {
        return AjaxResult.error(message);
    }

    /**
     * 响应返回结果。
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult<Void> toResult(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 响应返回结果。
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult<Void> toResult(boolean result) {
        return result ? success() : error();
    }

    /**
     * 获取用户缓存信息。
     */
    public LoginUser getLoginUser() {
        return SecurityUtils.getLoginUser();
    }
    
}
