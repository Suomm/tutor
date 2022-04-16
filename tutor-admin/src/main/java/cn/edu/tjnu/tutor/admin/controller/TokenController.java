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

package cn.edu.tjnu.tutor.admin.controller;

import cn.edu.tjnu.tutor.admin.config.CasClientConfig;
import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.common.core.service.LoginInfoService;
import cn.edu.tjnu.tutor.common.enums.ExceptionType;
import cn.edu.tjnu.tutor.common.enums.UserStatus;
import cn.edu.tjnu.tutor.common.exception.ServiceException;
import cn.edu.tjnu.tutor.common.provider.TokenProvider;
import cn.edu.tjnu.tutor.system.domain.entity.College;
import cn.edu.tjnu.tutor.system.domain.entity.User;
import cn.edu.tjnu.tutor.system.service.CollegeService;
import cn.edu.tjnu.tutor.system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Map;

import static cn.edu.tjnu.tutor.common.constant.GlobalConst.UTF_8;

/**
 * <p>令牌控制层。
 *
 * <p>前后端分离分布式微服务组件：
 *
 * <ul>
 *     <li>前端地址：https://localhost</li>
 *     <li>网关地址：http://localhost:5735</li>
 *     <li>授权地址：http://localhost:9377</li>
 *     <li>认证地址：http://localhost:8443/cas</li>
 * </ul>
 *
 * @author 王帅
 * @since 2.0
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
@ConditionalOnBean(CasClientConfig.class)
public class TokenController {

    private static final String USER_NAME = "cn";
    private static final String USER_CODE = "uid";
    private static final String COLLEGE_CODE = "eduPersonOrgDN";

    private static final String TOKEN = "?token=";
    private static final String SERVICE = "?service=";
    private static final String REDIRECT = "redirect:";

    private final TokenProvider tokenProvider;

    private final UserService userService;
    private final CollegeService collegeService;
    private final LoginInfoService loginInfoService;

    @Value("${spring.cas.callback-url}")
    private String callbackUrl;
    @Value("${spring.cas.client-host-url}")
    private String clientHostUrl;
    @Value("${spring.cas.server-logout-url}")
    private String serverLogoutUrl;

    /**
     * <p><b>CAS 登录流程：</b>
     *
     * <ul>
     *     <li>前端登录请求：https://localhost/api/login</li>
     *     <li>Nginx 反向代理到网关：http://localhost:5735/login</li>
     *     <li>网关将请求转发到授权服务器：http://localhost:9377/auth/login</li>
     *     <li>用户没有被授权时，授权服务器返回 401 UNAUTHORIZED 状态码并将认证地址返回给前端</li>
     *     <li>前端访问认证地址：https://localhost:8443/cas/login?service=URLEncode(https://localhost/auth/login)</li>
     *     <li>认证成功之后重定向到：https://localhost/auth/login?ticket=...</li>
     *     <li>Nginx 反向代理到网关：http://localhost:5735/login?ticket=...</li>
     *     <li>网关将请求转发到授权服务器：http://localhost:9377/auth/login?ticket=...</li>
     *     <li>授权服务器根据 Ticket 进行授权，从数据库查询用户并生成生成 Token 令牌</li>
     *     <li>授权服务器重定向到前端：https://localhost/login?token=...</li>
     *     <li>前端根据 URL 参数进行保存 Token 操作，携带令牌即可访问相关接口</li>
     * </ul>
     */
    @GetMapping("login")
    public String login(HttpServletRequest request) {
        // 获取 CAS 认证属性
        Map<String, Object> attributes = AssertionHolder.getAssertion()
                .getPrincipal()
                .getAttributes();
        // 获取学院主键
        College college = collegeService.lambdaQuery()
                .select(College::getCollegeId)
                .eq(College::getCollegeCode, attributes.get(COLLEGE_CODE))
                .oneOpt()
                .orElseThrow(() -> new ServiceException(ExceptionType.COLLEGE_NOT_REGISTER));
        // 如果没有登录信息，则注册这个用户
        LoginUser loginUser = userService.login(User.builder()
                .collegeId(college.getCollegeId())
                .userCode(attributes.get(USER_CODE).toString())
                .userName(attributes.get(USER_NAME).toString())
                .build());
        // 记录登录信息
        loginInfoService.recordLoginInfo(request, loginUser, UserStatus.LOGIN);
        // 重定向到前端地址
        return REDIRECT + callbackUrl + TOKEN + tokenProvider.createToken(loginUser);
    }

    /**
     * <p><b>CAS 登出流程：</b>
     *
     * <ul>
     *     <li>前端登出请求：https://localhost/api/logout</li>
     *     <li>Nginx 反向代理到网关：http://localhost:5735/logout</li>
     *     <li>网关将请求转发到授权服务器：http://localhost:9377/auth/logout</li>
     *     <li>授权服务器返回重定向地址，前端接收到地址后可以进行前端数据清理等操作</li>
     *     <li>前端进行重定向：https://localhost:8443/cas/logout?service=URLEncode(https://localhost)</li>
     *     <li>登出成功之后重定向到前端首页：https://localhost</li>
     * </ul>
     */
    @SneakyThrows
    @ResponseBody
    @PostMapping("logout")
    public String logout(HttpServletRequest request) {
        LoginUser loginUser = tokenProvider.getLoginUser(request);
        if (loginUser != null) {
            // 删除用户令牌信息
            tokenProvider.deleteToken(loginUser.getUuid());
            // 记录注销信息
            loginInfoService.recordLoginInfo(request, loginUser, UserStatus.LOGOUT);
        }
        // 返回认证注销地址
        return serverLogoutUrl + SERVICE + URLEncoder.encode(clientHostUrl, UTF_8);
    }

}