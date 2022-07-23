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

import cn.edu.tjnu.tutor.common.core.service.ExcelDataService;
import cn.edu.tjnu.tutor.system.domain.entity.Major;
import cn.edu.tjnu.tutor.system.domain.query.MajorQuery;
import cn.edu.tjnu.tutor.system.domain.view.MajorVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 专业信息服务层。
 *
 * @author 王帅
 * @since 2.0
 */
public interface MajorService extends IService<Major>, ExcelDataService<MajorVO, MajorQuery> {

    /**
     * 分页查询专业信息。
     *
     * @param query 分页查询对象
     * @return 分页对象
     */
    IPage<MajorVO> pageVO(MajorQuery query);

    /**
     * 判断是否包含给定的专业名称。
     *
     * @param majorName 专业名称
     * @return {@code true} 包含给定的专业名称，{@code false} 不包含给定的专业名称
     * @implSpec 对于专业信息服务，该默认实现为：
     * <pre>{@code
     * return lambdaQuery()
     *         .eq(Major::getMajorName, majorName)
     *         .exists();
     * }</pre>
     */
    default boolean containsName(String majorName) {
        return lambdaQuery()
                .eq(Major::getMajorName, majorName)
                .exists();
    }

    /**
     * 根据专业名称查询获取专业主键。
     *
     * @param majorName 学院名称
     * @return 专业主键，如果返回 {@code null} 则代表没有该专业信息
     * @implSpec 对于专业信息服务，该默认实现为：
     * <pre>{@code
     * return lambdaQuery()
     *         .select(Major::getMajorId)
     *         .eq(Major::getMajorName, majorName)
     *         .oneOpt()
     *         .map(Major::getMajorId)
     *         .orElse(null);
     * }</pre>
     */
    default Integer getMajorId(Object majorName) {
        return lambdaQuery()
                .select(Major::getMajorId)
                .eq(Major::getMajorName, majorName)
                .oneOpt()
                .map(Major::getMajorId)
                .orElse(null);
    }

}