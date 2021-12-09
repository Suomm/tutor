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

package cn.edu.tjnu.tutor.common.exception;

import lombok.Getter;

import java.util.Objects;

/**
 * 运行时异常的消息回显，是所有需要回显给用户的异常信息的父类。该异常会被全局异常解析器解析，
 * 回显错误的详细信息。
 *
 * @author 王帅
 * @since 1.0
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 7180827225792980724L;

    /**
     * 状态码。
     */
    @Getter
    private final Integer code;

    /**
     * 用指定的详细消息构造一个新的运行时异常。其中的详细消息用于回显到前端界面，
     * 详细消息的内容不应该为 {@code null}。
     *
     * @param message 需要回显的消息信息
     * @throws NullPointerException 如果回显详细消息为 {@code null}
     */
    public ServiceException(String message) {
        this(null, message);
    }

    /**
     * 用指定的详细消息构造一个新的运行时异常。其中的详细消息用于回显到前端界面，
     * 详细消息的内容不应该为 {@code null}。
     *
     * @param code 异常状态码
     * @param message 需要回显的消息信息
     * @throws NullPointerException 如果回显详细消息为 {@code null}
     */
    public ServiceException(Integer code, String message) {
        super(Objects.requireNonNull(message));
        this.code = code;
    }

}
