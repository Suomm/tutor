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

import cn.edu.tjnu.tutor.common.core.domain.query.PageQuery;
import cn.edu.tjnu.tutor.common.core.service.ExcelDataService;
import cn.edu.tjnu.tutor.system.domain.entity.College;
import cn.edu.tjnu.tutor.system.domain.view.CollegeVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 学院信息服务层。
 *
 * @author 王帅
 * @since 1.0
 */
public interface CollegeService extends IService<College>, ExcelDataService<CollegeVO, PageQuery> {

    /**
     * 分页查询学院信息。
     *
     * @param page 分页对象
     * @param <P>  分页对象类型
     * @return 分页数据
     */
    <P extends IPage<CollegeVO>> P pageVO(P page);

    /**
     * 判断是否包含给定的学院名称。
     *
     * @param collegeName 学院名称
     * @return {@code true} 包含给定的学院名称，{@code false} 不包含给定的学院名称
     * @implSpec 对于学院信息服务，该默认实现为：
     * <pre>{@code
     * return lambdaQuery()
     *         .eq(College::getCollegeName, collegeName)
     *         .exists();
     * }</pre>
     */
    default boolean containsName(String collegeName) {
        return lambdaQuery()
                .eq(College::getCollegeName, collegeName)
                .exists();
    }

    /**
     * 判断是否包含给定的学院编号。
     *
     * @param collegeCode 学院编号
     * @return {@code true} 包含给定的学院编号，{@code false} 不包含给定的学院编号
     * @implSpec 对于学院信息服务，该默认实现为：
     * <pre>{@code
     * return lambdaQuery()
     *         .eq(College::getCollegeCode, collegeCode)
     *         .exists();
     * }</pre>
     */
    default boolean containsCode(Integer collegeCode) {
        return lambdaQuery()
                .eq(College::getCollegeCode, collegeCode)
                .exists();
    }

    /**
     * 根据学院名称查询获取学院主键。
     *
     * @param collegeName 学院名称
     * @return 学院主键，如果返回 {@code null} 则代表没有该学院信息
     * @implSpec 对于学院信息服务，该默认实现为：
     * <pre>{@code
     * return lambdaQuery()
     *         .select(College::getCollegeId)
     *         .eq(College::getCollegeName, collegeName)
     *         .oneOpt()
     *         .map(College::getCollegeId)
     *         .orElse(null);
     * }</pre>
     */
    default Integer getCollegeId(Object collegeName) {
        return lambdaQuery()
                .select(College::getCollegeId)
                .eq(College::getCollegeName, collegeName)
                .oneOpt()
                .map(College::getCollegeId)
                .orElse(null);
    }

}