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

package cn.edu.tjnu.tutor.support.controller;

import cn.edu.tjnu.tutor.common.annotation.Log;
import cn.edu.tjnu.tutor.common.core.controller.BaseController;
import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.core.domain.PageQuery;
import cn.edu.tjnu.tutor.common.core.domain.Pagination;
import cn.edu.tjnu.tutor.common.util.PageUtils;
import cn.edu.tjnu.tutor.system.domain.entity.TheClass;
import cn.edu.tjnu.tutor.system.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_ADMIN;
import static cn.edu.tjnu.tutor.common.enums.Category.CLASS;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 班级信息控制层。
 *
 * @author 王帅
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/class")
public class ClassController extends BaseController {

    private final ClassService classService;

    /**
     * 分页查询班级信息。
     *
     * @param pageQuery 分页帮助
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<Pagination<TheClass>> list(PageQuery pageQuery) {
        return success(PageUtils.convert(classService.page(pageQuery.page())));
    }

    /**
     * 根据班级主键获取详细信息。
     *
     * @param classId 班级主键
     * @return 班级信息详情
     */
    @GetMapping("getInfo/{classId}")
    public AjaxResult<TheClass> getInfo(@PathVariable Integer classId) {
        return success(classService.getById(classId));
    }

    /**
     * 添加班级信息。
     *
     * @param theClass 班级信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @Secured(ROLE_ADMIN)
    @PostMapping("save")
    @Log(category = CLASS, operType = INSERT)
    public AjaxResult<Void> save(@Validated @RequestBody TheClass theClass) {
        return toResult(classService.save(theClass));
    }

    /**
     * 更新班级信息。
     *
     * @param theClass 班级信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Log(category = CLASS, operType = UPDATE)
    public AjaxResult<Void> update(@Validated @RequestBody TheClass theClass) {
        return toResult(classService.updateById(theClass));
    }

    /**
     * 根据班级主键删除班级信息。
     *
     * @param classId 班级主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{classId}")
    @Log(category = CLASS, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer classId) {
        return toResult(classService.removeById(classId));
    }

}