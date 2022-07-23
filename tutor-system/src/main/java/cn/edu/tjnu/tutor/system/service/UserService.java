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

package cn.edu.tjnu.tutor.system.service;

import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.common.core.service.ExcelDataService;
import cn.edu.tjnu.tutor.system.domain.entity.User;
import cn.edu.tjnu.tutor.system.domain.query.UserQuery;
import cn.edu.tjnu.tutor.system.domain.view.ProfileVO;
import cn.edu.tjnu.tutor.system.domain.view.UserVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户信息服务层。
 *
 * @author 王帅
 * @since 1.0
 */
public interface UserService extends IService<User>, ExcelDataService<UserVO, UserQuery> {

    /**
     * 学生学号的长度。
     */
    int LEN_STUDENT_CODE = 10;

    /**
     * 保存用户信息，并绑定用户对应的角色。
     *
     * @param user 用户信息
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    boolean saveAndBind(User user);

    /**
     * 更新用户信息，并更新用户对应的角色（如果需要的话）。
     *
     * @param user 用户信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    boolean updateAndBind(User user);

    /**
     * 用户登录并获取相应权限信息，如果用户不存在则注册用户。
     *
     * @param user 用户实体
     * @return 登录用户
     */
    LoginUser login(User user);

    /**
     * 注册用户信息。
     *
     * @param user 用户实体
     * @return {@code true} 注册成功，{@code false} 注册失败
     * @apiNote 该方法注册用户不会先查询用户是否存在。
     */
    boolean register(User user);

    /**
     * 根据用户主键获取用户所有信息。
     *
     * @param userId 用户主键
     * @return 用户所有信息
     */
    ProfileVO getProfile(Integer userId);

    /**
     * 分页查询用户信息。
     *
     * @param query 分页查询参数
     * @return 分页对象
     */
    IPage<UserVO> pageVO(UserQuery query);

    /**
     * 查询用户所对应的角色主键。
     *
     * @param userId 用户主键
     * @return 角色主键
     */
    List<Integer> roleIdList(Integer userId);

    /**
     * 根据用户编号判断是否存在此用户。
     *
     * @param userCode 用户编号
     * @return {@code true} 存在此用户，{@code false} 不存在此用户
     * @implSpec 对于用户信息服务，该默认实现为：
     * <pre>{@code
     * return lambdaQuery()
     *         .eq(User::getUserCode, userCode)
     *         .exists();
     * }</pre>
     */
    default boolean containsUser(String userCode) {
        return lambdaQuery()
                .eq(User::getUserCode, userCode)
                .exists();
    }

}