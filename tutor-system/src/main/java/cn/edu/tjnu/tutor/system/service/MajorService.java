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
public interface MajorService extends IService<Major>, ExcelDataService<MajorVO> {

    /**
     * 分页查询班级信息。
     *
     * @param query 分页查询对象
     * @return 分页对象
     */
    IPage<MajorVO> pageVO(MajorQuery query);

}