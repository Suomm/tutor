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

import cn.edu.tjnu.tutor.system.domain.entity.Config;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 参数配置服务层。
 *
 * @author 王帅
 * @since 2.0
 */
public interface ConfigService {

    /**
     * 分页查询参数配置信息。
     *
     * @param page 分页对象
     * @param <P>  分页对象类型
     * @return 分页数据
     */
    <P extends IPage<Config>> P page(P page);

    /**
     * 保存参数配置信息。
     *
     * @param config 参数配置信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     * @apiNote 保存操作成功后，同时会向缓存中保存参数配置信息。
     */
    boolean save(Config config);

    /**
     * 根据主键更新参数配置信息。
     *
     * @param config 参数配置信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     * @apiNote 更新操作成功后，同时会更新缓存中的参数配置信息。
     */
    boolean updateById(Config config);

    /**
     * 根据主键删除参数配置信息。
     *
     * @param configId 参数配置主键
     * @return {@code true} 添加成功，{@code false} 添加失败
     * @apiNote 删除操作成功后，同时会删除缓存中参数配置信息。
     */
    boolean removeById(Integer configId);

    /**
     * 判断是否包含给定的参数配置键值。
     *
     * @param configKey 参数配置键值
     * @return {@code true} 包含所给键值，{@code false} 不包含所给键值
     */
    boolean containsKey(String configKey);

    /**
     * 加载所有参数配置信息缓存。
     */
    void loadConfigCache();

    /**
     * 删除所有参数配置信息缓存。
     */
    void deleteConfigCache();

    /**
     * 重置所有参数配置信息缓存。
     *
     * @implSpec 对于参数配置信息服务，该默认实现为：
     * <pre>{@code
     * deleteConfigCache();
     * loadConfigCache();
     * }</pre>
     */
    default void resetConfigCache() {
        deleteConfigCache();
        loadConfigCache();
    }

}