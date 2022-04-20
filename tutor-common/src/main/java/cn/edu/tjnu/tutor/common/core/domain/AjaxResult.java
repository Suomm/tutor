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

package cn.edu.tjnu.tutor.common.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 操作消息结果提示。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AjaxResult<T> implements Serializable {

    private static final long serialVersionUID = -589774230401895753L;

    private static final int DEFAULT_ERROR_CODE   = 500;
    private static final int DEFAULT_SUCCESS_CODE = 200;

    private static final String DEFAULT_ERROR_MESSAGE   = "操作失败";
    private static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";

    /**
     * 默认请求失败结果返回。
     */
    public static final AjaxResult<Void> ERROR =
            new AjaxResult<>(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE, null);

    /**
     * 默认请求成功结果返回。
     */
    public static final AjaxResult<Void> SUCCESS =
            new AjaxResult<>(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, null);

    /**
     * 状态码。
     */
    private Integer code;

    /**
     * 返回内容。
     */
    private String message;

    /**
     * 数据对象。
     */
    private transient T data;

    /**
     * 返回成功数据。
     *
     * @return 成功消息
     */
    public static <T> AjaxResult<T> success(T data) {
        return success(DEFAULT_SUCCESS_MESSAGE, data);
    }

    /**
     * 返回成功消息。
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static  <T> AjaxResult<T> success(String msg, T data) {
        return new AjaxResult<>(DEFAULT_SUCCESS_CODE, msg, data);
    }

    /**
     * 返回错误消息。
     *
     * @param msg 返回内容
     * @return 错误消息
     */
    public static AjaxResult<Void> error(String msg) {
        return new AjaxResult<>(DEFAULT_ERROR_CODE, msg, null);
    }

    /**
     * 返回错误消息。
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 错误消息
     */
    public static AjaxResult<Void> error(int code, String msg) {
        return new AjaxResult<>(code, msg, null);
    }

}
