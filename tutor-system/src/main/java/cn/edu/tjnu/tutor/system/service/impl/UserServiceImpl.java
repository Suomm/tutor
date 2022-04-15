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
import cn.edu.tjnu.tutor.system.domain.entity.User;
import cn.edu.tjnu.tutor.system.domain.view.UserVO;
import cn.edu.tjnu.tutor.system.mapper.UserMapper;
import cn.edu.tjnu.tutor.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static cn.edu.tjnu.tutor.common.constant.GlobalConst.EMPTY_STRING_ARRAY;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_STUDENT;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_TEACHER;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

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
        LoginUser loginUser = baseMapper.selectByUserCode(user.getUserCode());
        // 查询不到用户编号则注册新用户
        if (loginUser == null) {
            loginUser = new LoginUser();
            // 先插入用户数据生成用户主键
            baseMapper.insert(user);
            // 根据用户编号特征绑定初始角色
            if (user.getUserCode().length() == LEN_STUDENT_CODE) {
                baseMapper.bindRoleForUser(user.getUserId(), ROLE_STUDENT);
                loginUser.setAuthorities(createAuthorityList(ROLE_STUDENT));
            } else {
                baseMapper.bindRoleForUser(user.getUserId(), ROLE_TEACHER);
                loginUser.setAuthorities(createAuthorityList(ROLE_TEACHER));
            }
            // 复制信息到空的当前登录用户
            loginUser.setUserId(user.getUserId());
            loginUser.setUserCode(user.getUserCode());
            loginUser.setCollegeId(user.getCollegeId());
        }
        // 权限信息为空则查询权限信息
        if (loginUser.getAuthorities() == null) {
            String[] roleKeys = baseMapper.selectRoleKeysById(loginUser.getUserId())
                    .toArray(EMPTY_STRING_ARRAY);
            loginUser.setAuthorities(createAuthorityList(roleKeys));
        }
        return loginUser;
    }

    @Override
    public UserVO getInfo(Integer userId) {
        return baseMapper.selectUserInfo(userId);
    }

}