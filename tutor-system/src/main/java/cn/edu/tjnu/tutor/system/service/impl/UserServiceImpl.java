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

import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.system.domain.User;
import cn.edu.tjnu.tutor.system.mapper.UserMapper;
import cn.edu.tjnu.tutor.system.service.UserService;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.STUDENT;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.TEACHER;

/**
 * 用户信息服务层实现。
 *
 * @author 王帅
 * @since 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoginUser login(User user) {
        LoginUser loginUser = new LoginUser();
        // 查询不到用户编号则注册新用户
        if (lambdaQuery()
                .eq(User::getUserCode, user.getUserCode())
                .count()
                .intValue() == 0) {
            // 先插入用户数据生成用户主键
            baseMapper.insert(user);
            // 根据用户编号特征绑定初始角色
            if (ReUtil.isMatch(RE_STUDENT_CODE, user.getUserCode())) {
                baseMapper.bindRoleForUser(user.getUserId(), STUDENT);
                loginUser.setAuthorities(AuthorityUtils.createAuthorityList(STUDENT));
            } else if (ReUtil.isMatch(RE_TEACHER_CODE, user.getUserCode())){
                baseMapper.bindRoleForUser(user.getUserId(), TEACHER);
                loginUser.setAuthorities(AuthorityUtils.createAuthorityList(TEACHER));
            }
        }
        BeanUtil.copyProperties(loginUser, user);
        // 权限信息为空则查询权限信息
        if (loginUser.getAuthorities() != null) {
            String[] roleKeys = baseMapper.selectRoleKeysByUserId(user.getUserId())
                    .toArray(String[]::new);
            loginUser.setAuthorities(AuthorityUtils.createAuthorityList(roleKeys));
        }
        return loginUser;
    }

}