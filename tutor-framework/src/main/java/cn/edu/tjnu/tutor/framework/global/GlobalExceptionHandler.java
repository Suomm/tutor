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

package cn.edu.tjnu.tutor.framework.global;

import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理器。
 *
 * @author 王帅
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截未知的异常信息。
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult<Void> handleException(Exception e, HttpServletRequest request) {
        log.error("请求地址 {} 发生了未知异常", request.getRequestURI(), e);
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 处理自定义异常中，将异常的详细信息回显到前端界面。
     *
     * @see ServiceException
     */
    @ExceptionHandler(ServiceException.class)
    public AjaxResult<Void> handleServiceException(ServiceException e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error(e.getLocalizedMessage());
    }

    /**
     * 自定义验证异常。
     */
    @ExceptionHandler({
            BindException.class,
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    public AjaxResult<Void> handleValidationException(Exception e) {
        log.error(e.getMessage(), e);
        return AjaxResult.error("ExceptionType.DATA_ENTITY_NOT_VALID");
    }

}
