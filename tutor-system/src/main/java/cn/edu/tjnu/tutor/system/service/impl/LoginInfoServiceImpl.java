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

import cn.edu.tjnu.tutor.common.core.service.LoginInfoService;
import cn.edu.tjnu.tutor.common.enums.UserStatus;
import cn.edu.tjnu.tutor.common.util.IpUtils;
import cn.edu.tjnu.tutor.system.domain.model.LoginInfo;
import cn.edu.tjnu.tutor.system.repository.LoginInfoRepository;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static cn.hutool.core.text.CharSequenceUtil.SPACE;

/**
 * 登陆记录服务层实现。
 *
 * @author 王帅
 * @since 2.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginInfoServiceImpl implements LoginInfoService {

    private static final String USER_AGENT = "User-Agent";

    private final LoginInfoRepository repository;

    @Async
    @Override
    public void recordLoginInfo(HttpServletRequest request, UserDetails userDetails, UserStatus status) {
        // 用 Debug 日志级别来记录用户登录/注销信息
        if (log.isDebugEnabled()) {
            log.debug("编号为 {} 的用户{}成功", userDetails.getUsername(), status.getStatus());
        }
        // 解析用户设备的相关信息
        String ip = IpUtils.getClientIp(request);
        UserAgent ua = UserAgentUtil.parse(request.getHeader(USER_AGENT));
        // 将登陆/注销状态存入 ES 中方便数据分析
        repository.insert(LoginInfo.builder()
                .ipaddr(ip)
                .status(status.name())
                .loginTime(LocalDateTime.now())
                .address(IpUtils.getIpAddress(ip))
                .userCode(userDetails.getUsername())
                .os(ua.getOs() + SPACE + ua.getOsVersion())
                .browser(ua.getBrowser() + SPACE + ua.getVersion())
                .engine(ua.getEngine() + SPACE + ua.getEngineVersion())
                .build());
    }

}