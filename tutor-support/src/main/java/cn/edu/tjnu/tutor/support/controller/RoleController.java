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
import cn.edu.tjnu.tutor.common.helper.PageHelper;
import cn.edu.tjnu.tutor.system.domain.entity.Role;
import cn.edu.tjnu.tutor.system.service.RoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.enums.Category.ROLE;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 角色信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController extends BaseController {

    private final RoleService roleService;

    /**
     * 分页查询角色信息。
     *
     * @param pageHelper 分页帮助
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<Page<Role>> list(PageHelper pageHelper) {
        return success(roleService.page(pageHelper.mybatisPlus()));
    }

    /**
     * 根据角色主键获取详细信息。
     *
     * @param roleId 角色主键
     * @return 角色信息详情
     */
    @GetMapping("getInfo/{roleId}")
    public AjaxResult<Role> getInfo(@PathVariable Integer roleId) {
        return success(roleService.getById(roleId));
    }

    /**
     * 添加角色信息。
     *
     * @param role 角色信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Log(category = ROLE, operType = INSERT)
    public AjaxResult<Void> save(@Validated @RequestBody Role role) {
        return toResult(roleService.save(role));
    }

    /**
     * 更新角色信息。
     *
     * @param role 角色信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Log(category = ROLE, operType = UPDATE)
    public AjaxResult<Void> update(@Validated @RequestBody Role role) {
        return toResult(roleService.updateById(role));
    }

    /**
     * 根据角色主键删除角色信息。
     *
     * @param roleId 角色主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{roleId}")
    @Log(category = ROLE, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer roleId) {
        return toResult(roleService.removeById(roleId));
    }

}