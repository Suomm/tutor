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

import cn.edu.tjnu.tutor.common.enums.ExceptionType;

/**
 * 服务层异常信息，会被全局异常解析器解析，回显错误的详细信息。
 *
 * @author 王帅
 * @since 1.0
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 7180827225792980724L;

    /**
     * 用指定的异常类型构造一个新的运行时异常。
     *
     * @param exceptionType 异常的类型
     * @param args          格式化参数
     */
    public ServiceException(ExceptionType exceptionType, Object... args) {
        super(exceptionType.getMessage(args));
    }

    /**
     * 用指定的引发原因和异常类型构造一个新的运行时异常。
     *
     * @param cause         引发原因
     * @param exceptionType 异常类型
     * @param args          格式化参数
     */
    public ServiceException(Throwable cause, ExceptionType exceptionType, Object... args) {
        super(exceptionType.getMessage(args), cause);
    }

}
