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
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.common.util.AopUtils;
import cn.edu.tjnu.tutor.common.util.PageUtils;
import cn.edu.tjnu.tutor.common.util.SqlUtils;
import cn.edu.tjnu.tutor.system.domain.entity.User;
import cn.edu.tjnu.tutor.system.domain.extra.UserRole;
import cn.edu.tjnu.tutor.system.domain.query.UserQuery;
import cn.edu.tjnu.tutor.system.domain.view.ProfileVO;
import cn.edu.tjnu.tutor.system.domain.view.UserVO;
import cn.edu.tjnu.tutor.system.mapper.UserMapper;
import cn.edu.tjnu.tutor.system.mapper.UserRoleMapper;
import cn.edu.tjnu.tutor.system.service.CollegeService;
import cn.edu.tjnu.tutor.system.service.UserService;
import cn.edu.tjnu.tutor.system.structure.UserStruct;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserStruct userStruct;
    private final UserRoleMapper userRoleMapper;
    private final CollegeService collegeService;

    @Override
    public boolean saveAndBind(User user) {
        return save(user) && insertUserRole(user.getUserId(), user.getRoleIds());
    }

    @Override
    public boolean updateAndBind(User user) {
        boolean inserted = true;
        if (ArrayUtil.isNotEmpty(user.getRoleIds())) {
            userRoleMapper.deleteById(user.getUserId());
            inserted = insertUserRole(user.getUserId(), user.getRoleIds());
        }
        return inserted && updateById(user);
    }

    private boolean insertUserRole(Integer userId, Integer[] roleIds) {
        List<UserRole> entities = Arrays.stream(roleIds)
                .map(roleId -> new UserRole(userId, roleId))
                .collect(Collectors.toList());
        return SqlUtils.toBool(userRoleMapper.insertBatch(entities));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoginUser login(User user) {
        LoginUser loginUser = baseMapper.selectByUserCode(user.getUserCode());
        // 查询不到用户编号则注册新用户
        if (loginUser == null) {
            register(user);
            loginUser = new LoginUser();
            loginUser.setUserId(user.getUserId());
            loginUser.setUserCode(user.getUserCode());
            loginUser.setCollegeId(user.getCollegeId());
        }
        // 查询角色信息
        String[] roleKeys = baseMapper.selectRoleKeysByUserId(loginUser.getUserId())
                .toArray(EMPTY_STRING_ARRAY);
        loginUser.setAuthorities(createAuthorityList(roleKeys));
        return loginUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(User user) {
        // 先插入用户数据生成用户主键
        if (!save(user)) {
            return false;
        }
        // 根据用户编号特征绑定初始角色
        if (user.getUserCode().length() == LEN_STUDENT_CODE) {
            return SqlUtils.toBool(userRoleMapper.insertWithRoleKey(user.getUserId(), ROLE_STUDENT));
        } else {
            return SqlUtils.toBool(userRoleMapper.insertWithRoleKey(user.getUserId(), ROLE_TEACHER));
        }
    }

    @Override
    public ProfileVO getProfile(Integer userId) {
        return baseMapper.selectUserInfo(userId);
    }

    @Override
    public IPage<UserVO> pageVO(UserQuery query) {
        return baseMapper.selectPageVO(query.page(), query);
    }

    @Override
    public List<Integer> roleIdList(Integer userId) {
        return ChainWrappers.lambdaQueryChain(userRoleMapper)
                .select(UserRole::getRoleId)
                .eq(UserRole::getUserId, userId)
                .list()
                .stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
    }

    @Override
    public Class<UserVO> getExcelHead() {
        return UserVO.class;
    }

    @Override
    public PageVO<UserVO> getRealData(UserQuery query) {
        return PageUtils.convert(pageVO(query));
    }

    @Override
    public boolean saveExcelData(UserVO vo, Map<Object, Object> cachedMap) {
        Object collegeId = cachedMap.computeIfAbsent(vo.getCollegeName(), collegeService::getCollegeId);
        if (collegeId != null) {
            User user = userStruct.toEntity(vo);
            user.setCollegeId((Integer) collegeId);
            return AopUtils.getProxy(UserService.class).register(user);
        }
        return false;
    }

}