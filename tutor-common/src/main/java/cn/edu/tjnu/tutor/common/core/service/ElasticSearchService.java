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

package cn.edu.tjnu.tutor.common.core.service;

import cn.easyes.core.biz.PageInfo;
import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import cn.easyes.core.conditions.LambdaEsUpdateWrapper;
import cn.easyes.core.conditions.interfaces.BaseEsMapper;
import cn.edu.tjnu.tutor.common.core.domain.query.PageQuery;
import cn.edu.tjnu.tutor.common.util.SqlUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Elasticsearch 通用服务层接口。
 *
 * @author 王帅
 * @since 2.0
 */
public interface ElasticSearchService<T> {

    /**
     * 获取基本映射器。
     *
     * @return {@link BaseEsMapper}
     */
    BaseEsMapper<T> getBaseMapper();

    /**
     * 分页查询数据。
     *
     * @param wrapper   包装器
     * @param pageQuery 分页参数
     * @return 分页对象
     * @implSpec 对于 Elasticsearch 通用服务接口，该默认实现为：
     * <pre>{@code
     * return getBaseMapper().pageQuery(wrapper, pageQuery.getPageNum(), pageQuery.getPageSize());
     * }</pre>
     */
    default PageInfo<T> page(LambdaEsQueryWrapper<T> wrapper, PageQuery pageQuery) {
        return getBaseMapper().pageQuery(wrapper, pageQuery.getPageNum(), pageQuery.getPageSize());
    }

    /**
     * 查询多条数据。
     *
     * @param wrapper 包装器
     * @return 数据集合
     * @implSpec 对于 Elasticsearch 通用服务接口，该默认实现为：
     * <pre>{@code
     * return getBaseMapper().selectList(wrapper);
     * }</pre>
     */
    default List<T> selectList(LambdaEsQueryWrapper<T> wrapper) {
        return getBaseMapper().selectList(wrapper);
    }

    /**
     * 保存一条数据。
     *
     * @param entity 实体
     * @return {@code true} 保存成功，{@code false} 保存失败。
     * @implSpec 对于 Elasticsearch 通用服务接口，该默认实现为：
     * <pre>{@code
     * return SqlUtils.toBool(getBaseMapper().insert(entity));
     * }</pre>
     */
    default boolean save(T entity) {
        return SqlUtils.toBool(getBaseMapper().insert(entity));
    }

    /**
     * 更新一条数据。
     *
     * @param entity  实体
     * @param wrapper 包装器
     * @return {@code true} 更新成功，{@code false} 更新失败。
     * @implSpec 对于 Elasticsearch 通用服务接口，该默认实现为：
     * <pre>{@code
     * return SqlUtils.toBool(getBaseMapper().update(entity, wrapper));
     * }</pre>
     */
    default boolean update(T entity, LambdaEsUpdateWrapper<T> wrapper) {
        return SqlUtils.toBool(getBaseMapper().update(entity, wrapper));
    }

    /**
     * 通过查询参数删除一条数据。
     *
     * @param wrapper 包装器
     * @return {@code true} 删除成功，{@code false} 删除失败。
     * @implSpec 对于 Elasticsearch 通用服务接口，该默认实现为：
     * <pre>{@code
     * return SqlUtils.toBool(getBaseMapper().delete(wrapper));
     * }</pre>
     */
    default boolean delete(LambdaEsQueryWrapper<T> wrapper) {
        return SqlUtils.toBool(getBaseMapper().delete(wrapper));
    }

    /**
     * 通过实体类主键删除一条数据。
     *
     * @param id 实体类主键
     * @return {@code true} 删除成功，{@code false} 删除失败。
     * @implSpec 对于 Elasticsearch 通用服务接口，该默认实现为：
     * <pre>{@code
     * return SqlUtils.toBool(getBaseMapper().deleteById(id));
     * }</pre>
     */
    default boolean deleteById(Serializable id) {
        return SqlUtils.toBool(getBaseMapper().deleteById(id));
    }

    /**
     * 返回 {@link LambdaEsQueryWrapper} 用于构建搜索查询参数。
     *
     * @return {@link LambdaEsQueryWrapper}
     * @implSpec 对于 Elasticsearch 通用服务接口，该默认实现为：
     * <pre>{@code
     * return new LambdaEsQueryWrapper<>();
     * }</pre>
     */
    default LambdaEsQueryWrapper<T> lambdaQuery() {
        return new LambdaEsQueryWrapper<>();
    }

    /**
     * 返回 {@link LambdaEsUpdateWrapper} 用于构建更新查询参数。
     *
     * @return {@link LambdaEsUpdateWrapper}
     * @implSpec 对于 Elasticsearch 通用服务接口，该默认实现为：
     * <pre>{@code
     * return new LambdaEsUpdateWrapper<>();
     * }</pre>
     */
    default LambdaEsUpdateWrapper<T> lambdaUpdate() {
        return new LambdaEsUpdateWrapper<>();
    }

}