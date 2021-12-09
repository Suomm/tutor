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

package cn.edu.tjnu.tutor.common.core.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * 当前登录用户。
 *
 * @author 王帅
 * @since 2.0
 */
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 5501326495340971354L;


    /**
     * 获取账户权限信息。
     *
     * @return 总是返回 {@code Collections.emptyList()}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    /**
     * 获取账户的密码。
     *
     * @return 总是返回 {@code null}
     */
    @Override
    @JsonIgnore
    public String getPassword() {
        return null;
    }


    /**
     * 获取账户的用户名。
     *
     * @return 返回账户的学号或工号
     */
    @Override
    @JsonIgnore
    public String getUsername() {
        return null;
    }

    /**
     * 账户是否未过期，过期无法验证。
     *
     * @return 总是返回 {@code true}
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁，锁定的用户无法进行身份验证。
     *
     * @return 总是返回 {@code true}
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码)，过期的凭据防止认证。
     *
     * @return 总是返回 {@code true}
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用，禁用的用户不能身份验证。
     *
     * @return 总是返回 {@code true}
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

}
