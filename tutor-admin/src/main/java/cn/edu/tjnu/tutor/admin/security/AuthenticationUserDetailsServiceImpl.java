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

package cn.edu.tjnu.tutor.admin.security;

import cn.edu.tjnu.tutor.common.enums.ExceptionType;
import cn.edu.tjnu.tutor.common.exception.ServiceException;
import cn.edu.tjnu.tutor.system.domain.entity.College;
import cn.edu.tjnu.tutor.system.domain.entity.User;
import cn.edu.tjnu.tutor.system.service.CollegeService;
import cn.edu.tjnu.tutor.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * CAS SSO 登录成功后的用户认证。
 *
 * @author 王帅
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class AuthenticationUserDetailsServiceImpl implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    private final UserService userService;

    private final CollegeService collegeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) {
        // 获取 CAS 登录成功之后返回的信息
        Map<String, Object> attributes = token.getAssertion().getPrincipal().getAttributes();
        // 获取学院主键
        College college = collegeService.lambdaQuery()
                .select(College::getCollegeId)
                .eq(College::getCollegeCode, attributes.get("eduPersonOrgDN"))
                .oneOpt()
                .orElseThrow(() -> new ServiceException(ExceptionType.COLLEGE_NOT_REGISTER));
        // 构建基本用户实体
        User user = User.builder()
                .collegeId(college.getCollegeId())
                .userCode((String) attributes.get("uid"))
                .userName((String) attributes.get("cn"))
                .build();
        // 登录并返回当前登录用户信息
        return userService.login(user);
    }

}
