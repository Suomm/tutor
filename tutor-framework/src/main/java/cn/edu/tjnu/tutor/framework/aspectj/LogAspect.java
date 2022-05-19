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

package cn.edu.tjnu.tutor.framework.aspectj;

import cn.edu.tjnu.tutor.common.annotation.Log;
import cn.edu.tjnu.tutor.common.core.service.OperLogService;
import cn.edu.tjnu.tutor.common.util.SecurityUtils;
import cn.edu.tjnu.tutor.common.util.ServletUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 日志切面。
 *
 * @author 王帅
 * @since 2.0
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {

    private static final String HASH = "#";
    private static final String BRACKET = "()";

    private final OperLogService operLogService;

    /**
     * 处理日志记录。
     */
    private void handleLog(JoinPoint joinPoint, Log controllerLog, Exception e, Object jsonResult) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String method = className + HASH + methodName + BRACKET;
        operLogService.recordOperLog(ServletUtils.getRequest(), SecurityUtils.getLoginUser(),
                controllerLog, method, joinPoint.getArgs(), e, jsonResult);
    }

    /**
     * 处理完请求后执行日志记录。
     *
     * @param joinPoint     切点
     * @param controllerLog 日志注解
     * @param jsonResult    请求结果
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * 拦截异常操作记录日志。
     *
     * @param joinPoint     切点
     * @param controllerLog 日志注解
     * @param e             异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

}
