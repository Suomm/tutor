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
import cn.edu.tjnu.tutor.common.core.domain.dto.OperLogDTO;
import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.common.enums.OperStatus;
import cn.edu.tjnu.tutor.common.util.SecurityUtils;
import cn.edu.tjnu.tutor.common.util.ServletUtils;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

/**
 * 日志切面。
 *
 * @author 王帅
 * @since 2.0
 */
@Slf4j
@Aspect
@Component
@SuppressWarnings("unused")
public class LogAspect {

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
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    private void handleLog(JoinPoint joinPoint, Log controllerLog, Exception e, Object jsonResult) {
        try {
            // 获取当前的用户
            LoginUser loginUser = SecurityUtils.getLoginUser();
            // 日志信息
            OperLogDTO operLog = new OperLogDTO();
            operLog.setStatus(OperStatus.SUCCESS.ordinal());
            // 请求的地址
            String ip = ServletUtils.getClientIp();
            operLog.setOperIp(ip);
            operLog.setOperUrl(ServletUtils.getRequest().getRequestURI());
            // 操作人员的 ID
            if (loginUser != null) {
                operLog.setOperId(loginUser.getUsername());
            }
            // 是否为错误日志
            if (e != null) {
                operLog.setStatus(OperStatus.FAIL.ordinal());
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            operLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operLog, jsonResult);
            // 保存数据库
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("前置通知异常：{}，异常信息:{}", exp.getClass().getName(), exp.getMessage());
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint     切点
     * @param log           日志注解
     * @param operLog       操作日志
     * @param jsonResult    请求结果
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, OperLogDTO operLog, Object jsonResult) {
        // 设置操作类型
        operLog.setOperType(log.operType().ordinal());
        // 设置标题
        operLog.setTitle(log.category().getName());
        // 是否需要保存 Request 中的参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operLog);
        }
        // 是否需要保存 Response 中的参数和值
        if (log.isSaveResponseData() && jsonResult != null) {
            operLog.setJsonResult(StringUtils.substring(JSONUtil.toJsonStr(jsonResult), 0, 2000));
        }
    }

    /**
     * 获取请求的参数，放到日志中。
     *
     * @param joinPoint 切点
     * @param operLog   操作日志
     */
    private void setRequestValue(JoinPoint joinPoint, OperLogDTO operLog) {
        String requestMethod = operLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operLog.setOperParam(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operLog.setOperParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    /**
     * PUT、POST参数拼装。
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (o != null) {
                    try {
                        params.append(JSONUtil.toJsonStr(o)).append(" ");
                    } catch (Exception e) {
                        // do nothing here.
                    }
                }
            }
        }
        return params.toString().trim();
    }

}
