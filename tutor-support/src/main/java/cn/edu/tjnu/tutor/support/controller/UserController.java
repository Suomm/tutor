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
import cn.edu.tjnu.tutor.common.core.domain.query.PageQuery;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import cn.edu.tjnu.tutor.system.domain.entity.User;
import cn.edu.tjnu.tutor.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_STUDENT;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_TEACHER;
import static cn.edu.tjnu.tutor.common.enums.Category.USER;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 用户信息控制层。
 *
 * @author 王帅
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    /**
     * 查询用户信息。
     *
     * @param pageQuery 分页参数
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<PageVO<User>> list(@Validated PageQuery pageQuery) {
        return pageSuccess(userService.page(pageQuery.page()));
    }

    /**
     * 查询用户详细信息。
     *
     * @param userId 用户主键|1
     * @return 用户信息详情
     */
    @GetMapping("getInfo/{userId}")
    @Secured({ROLE_STUDENT, ROLE_TEACHER})
    public AjaxResult<User> getInfo(@PathVariable Integer userId) {
        return success(userService.getById(userId));
    }

    /**
     * 添加用户信息。
     *
     * @param user 用户信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Log(category = USER, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) User user) {
        return toResult(userService.save(user));
    }

    /**
     * 更新用户信息。
     *
     * @param user 用户信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @PutMapping("update")
    @Log(category = USER, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) User user) {
        return toResult(userService.updateById(user));
    }

    /**
     * 删除用户信息。
     *
     * @param userId 用户主键|1
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @DeleteMapping("remove/{userId}")
    @Log(category = USER, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer userId) {
        return toResult(userService.removeById(userId));
    }

}