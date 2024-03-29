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
import cn.edu.tjnu.tutor.system.domain.entity.TheClass;
import cn.edu.tjnu.tutor.system.domain.query.ClassQuery;
import cn.edu.tjnu.tutor.system.domain.view.ClassVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 班级信息服务层。
 *
 * @author 王帅
 * @since 1.0
 */
public interface ClassService extends IService<TheClass>, ExcelDataService<ClassVO, ClassQuery> {

    /**
     * 分页查询班级信息。
     *
     * @param query 分页查询参数
     * @return 分页对象
     */
    IPage<ClassVO> pageVO(ClassQuery query);

    /**
     * 判断是否包含给定的班级名称。
     *
     * @param className 班级名称
     * @return {@code true} 包含给定的班级名称，{@code false} 不包含给定的班级名称
     * @implSpec 对于班级信息服务，该默认实现为：
     * <pre>{@code
     * return lambdaQuery()
     *         .eq(TheClass::getClassName, className)
     *         .exists();
     * }</pre>
     */
    default boolean containsName(String className) {
        return lambdaQuery()
                .eq(TheClass::getClassName, className)
                .exists();
    }

}