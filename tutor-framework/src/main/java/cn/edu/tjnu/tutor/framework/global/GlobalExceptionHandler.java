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
     * 拦截未知的异常信息。
     */
    @ExceptionHandler(Exception.class)
    public AjaxResult<Void> handleException(Exception e, HttpServletRequest request) {
        log.error("请求地址 {} 发生了未知异常", request.getRequestURI(), e);
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 处理自定义异常，将异常的详细信息回显到前端界面。
     *
     * @see ServiceException
     */
    @ExceptionHandler(ServiceException.class)
    public AjaxResult<Void> handleServiceException(ServiceException e) {
        return AjaxResult.error(e.getMessage());
    }

    /**
     * 处理自定义校验异常。
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult<Void> handleBindException(BindException e) {
        String message = e.getFieldErrors()
                .stream()
                .map(o -> o.getField() + ": " + o.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return AjaxResult.error(message);
    }

    /**
     * 处理自定义校验异常。
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public AjaxResult<Void> handleConstraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations()
                .stream()
                .map(o -> o.getPropertyPath() + ": " + o.getMessage())
                .collect(Collectors.joining(", "));
        return AjaxResult.error(message);
    }

    /**
     * 处理自定义校验异常。
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(o -> o.getField() + ": " + o.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return AjaxResult.error(message);
    }

}
