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

package cn.edu.tjnu.tutor.admin.controller;

import cn.edu.tjnu.tutor.common.annotation.Log;
import cn.edu.tjnu.tutor.common.core.controller.BaseController;
import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.core.domain.query.PageQuery;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import cn.edu.tjnu.tutor.system.domain.entity.College;
import cn.edu.tjnu.tutor.system.domain.meta.CollegeMeta;
import cn.edu.tjnu.tutor.system.service.CollegeService;
import cn.edu.tjnu.tutor.system.structure.CollegeStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.enums.Category.COLLEGE;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 学院信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/college")
public class CollegeController extends BaseController {

    private final CollegeStruct collegeStruct;
    private final CollegeService collegeService;

    /**
     * 查询学院信息。
     *
     * @param pageQuery 分页参数
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<PageVO<College>> list(@Validated PageQuery pageQuery) {
        return pageSuccess(collegeService.page(pageQuery.page()));
    }

    /**
     * 添加学院信息。
     *
     * @param collegeMeta 学院信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Log(category = COLLEGE, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) CollegeMeta collegeMeta) {
        return toResult(collegeService.save(collegeStruct.toEntity(collegeMeta)));
    }

    /**
     * 更新学院信息。
     *
     * @param collegeMeta 学院信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @PutMapping("update")
    @Log(category = COLLEGE, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) CollegeMeta collegeMeta) {
        return toResult(collegeService.updateById(collegeStruct.toEntity(collegeMeta)));
    }

    /**
     * 删除学院信息。
     *
     * @param collegeId 学院主键|1
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     * @apiNote 如果所要删除的学院下包含专业信息，或者已经有学生注册到学院中等情况时，
     * 该学院将无法被删除，直接返回没有详细错误描述的错误状态码。
     */
    @DeleteMapping("remove/{collegeId}")
    @Log(category = COLLEGE, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer collegeId) {
        return toResult(collegeService.removeById(collegeId));
    }

}