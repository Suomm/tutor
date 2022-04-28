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

package cn.edu.tjnu.tutor.system.service.impl;

import cn.edu.tjnu.tutor.common.annotation.Log;
import cn.edu.tjnu.tutor.common.core.service.OperLogService;
import cn.edu.tjnu.tutor.common.enums.OperStatus;
import cn.edu.tjnu.tutor.common.util.IpUtils;
import cn.edu.tjnu.tutor.system.domain.model.OperLog;
import cn.edu.tjnu.tutor.system.repository.OperLogRepository;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 操作日志服务层实现。
 *
 * @author 王帅
 * @since 2.0
 */
@Service
@RequiredArgsConstructor
public class OperLogServiceImpl implements OperLogService {

    private final OperLogRepository repository;

    @Async
    @Override
    public void recordOperLog(HttpServletRequest request, UserDetails userDetails,
                              Log log, String method, Object[] paramsArray,
                              Exception e, Object jsonResult) {
        // 创建日志记录实体类
        OperLog operLog = OperLog.builder()
                .method(method)
                .httpMethod(request.getMethod())
                .category(log.category().getName())
                .operIp(IpUtils.getClientIp(request))
                .operUrl(request.getRequestURI())
                .operTime(LocalDateTime.now())
                .operType(log.operType().name())
                .operStatus(OperStatus.SUCCESS.name())
                .build();
        // 用户信息不为空
        if (userDetails != null) {
            operLog.setUserCode(userDetails.getUsername());
        }
        // 请求处理时发生了异常
        if (e != null) {
            operLog.setErrorMsg(e.getMessage());
            operLog.setOperStatus(OperStatus.ERROR.name());
        }
        // 是否需要保存 Request 中的参数和值
        if (log.isSaveRequestData()) {
            operLog.setOperParam(JSONUtil.toJsonStr(paramsArray));
        }
        // 是否需要保存 Response 中的参数和值
        if (log.isSaveResponseData() && jsonResult != null) {
            operLog.setJsonResult(JSONUtil.toJsonStr(jsonResult));
        }
        // 将日志存入 ES 中方便数据分析
        repository.save(operLog);
    }

}