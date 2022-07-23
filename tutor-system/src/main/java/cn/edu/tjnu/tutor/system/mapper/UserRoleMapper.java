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

package cn.edu.tjnu.tutor.system.mapper;

import cn.edu.tjnu.tutor.system.domain.extra.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户和角色关联数据层。
 *
 * @author 王帅
 * @since 2.0
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 批量插入数据。
     *
     * @param entities 实体类集合
     * @return 受影响行数
     */
    int insertBatch(List<UserRole> entities);

    /**
     * 插入用户角色绑定数据。
     *
     * @param userId  用户主键
     * @param roleKey 角色键值
     * @return {@code 1} 绑定成功，否则失败
     */
    int insertWithRoleKey(@Param("userId") Integer userId, @Param("roleKey") String roleKey);

}