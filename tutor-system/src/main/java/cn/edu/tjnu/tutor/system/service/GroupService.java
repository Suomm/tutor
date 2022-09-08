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

import cn.edu.tjnu.tutor.system.domain.entity.Group;
import cn.edu.tjnu.tutor.system.domain.view.GroupVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 导师小组信息服务层。
 *
 * @author 王帅
 * @since 2.0
 */
public interface GroupService extends IService<Group> {


    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @param role   角色
     * @param page   页面
     * @return 分页对象
     */
    <E extends IPage<GroupVO>> E getUserInfo(Integer userId, String role, E page);

    /**
     * 分页查询导师小组信息。
     *
     * @param userId 导师用户主键
     * @param page   分页对象
     * @param <E>    分页对象类型
     * @return 分页数据
     */
    <E extends IPage<GroupVO>> E pageVO(Integer userId, E page);

    /**
     * 判断两个用户是否在同一组。
     *
     * @param userId      用户主键
     * @param otherUserId 另一个用户主键
     * @return {@code true} 两个用户在同一小组，{@code false} 两个用户不在同一小组
     */
    boolean notInSameGroup(Integer userId, Integer otherUserId);

    /**
     * 判断是否包含给定的导师小组名称。
     *
     * @param group 导师小组信息
     * @return {@code true} 包含给定的导师小组名称，{@code false} 不包含给定的导师小组名称
     * @implSpec 对于导师小组信息服务，该默认实现为：
     * <pre>{@code
     * return lambdaQuery()
     *         .eq(Group::getUserId, group.getUserId())
     *         .eq(Group::getGroupName, group.getGroupName())
     *         .exists();
     * }</pre>
     */
    default boolean containsName(Group group) {
        return lambdaQuery()
                .eq(Group::getUserId, group.getUserId())
                .eq(Group::getGroupName, group.getGroupName())
                .exists();
    }

}