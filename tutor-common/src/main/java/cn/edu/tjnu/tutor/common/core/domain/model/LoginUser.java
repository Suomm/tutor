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

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 当前登录用户。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class LoginUser implements UserDetails {

    private static final long serialVersionUID = 5501326495340971354L;

    /**
     * 用户主键。
     */
    private Integer userId;

    /**
     * 所属学院主键。
     */
    private Integer collegeId;

    /**
     * 用户编号（学号，工号）。
     */
    private String userCode;

    /**
     * 登录过期时间。
     */
    private Long expireTime;

    /**
     * 用户权限信息。
     */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 获取用户权限信息。
     *
     * @return 用户权限信息
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * 获取用户的密码。
     *
     * @return 总是返回 {@code null}
     */
    @Override
    public String getPassword() {
        return null;
    }


    /**
     * 获取用户的用户名。
     *
     * @return 返回用户的学号或工号
     */
    @Override
    public String getUsername() {
        return this.userCode;
    }

    /**
     * 用户是否未过期，过期无法验证。
     *
     * @return 总是返回 {@code true}
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁，锁定的用户无法进行身份验证。
     *
     * @return 总是返回 {@code true}
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码)，过期的凭据防止认证。
     *
     * @return 总是返回 {@code true}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用，禁用的用户不能身份验证。
     *
     * @return 总是返回 {@code true}
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
