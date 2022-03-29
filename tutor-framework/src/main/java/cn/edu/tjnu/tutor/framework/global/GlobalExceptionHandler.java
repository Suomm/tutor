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
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

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
     * 请求方式不支持。
     *
     * @see HttpRequestMethodNotSupportedException
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public AjaxResult<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                                HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestUri, e.getMethod());
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 处理自定义异常中，将异常的详细信息回显到前端界面。返回 HTTP 400 状态码，
     * 表示请求因出现异常而未被处理，并包含响应文本 responseText 表示异常的详
     * 细消息。
     *
     * @see ServiceException
     */
    @ExceptionHandler(ServiceException.class)
    public AjaxResult<Void> handleServiceException(ServiceException e) {
        log.error(e.getMessage(), e);
        Integer code = e.getCode();
        return code != null ? AjaxResult.error(code, e.getMessage()) : AjaxResult.error(e.getMessage());
    }

    /**
     * 自定义验证异常。
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult<Void> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return AjaxResult.error(message);
    }

    /**
     * 自定义验证异常。
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public AjaxResult<Void> constraintViolationException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        return AjaxResult.error(message);
    }

    /**
     * 自定义验证异常。
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return AjaxResult.error(message);
    }

}
