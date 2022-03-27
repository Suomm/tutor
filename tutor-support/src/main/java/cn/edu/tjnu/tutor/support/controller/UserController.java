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
import cn.edu.tjnu.tutor.system.domain.User;
import cn.edu.tjnu.tutor.system.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.STUDENT;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.TEACHER;
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
     * 分页查询用户信息。
     *
     * @param pageHelper 分页帮助
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<Page<User>> list(PageHelper pageHelper) {
        return AjaxResult.success(userService.page(pageHelper.mybatisPlus()));
    }

    /**
     * 根据用户主键获取详细信息。
     *
     * @param userId 用户主键
     * @return 用户信息详情
     */
    @GetMapping("getInfo/{userId}")
    @Secured({STUDENT, TEACHER})
    public AjaxResult<User> getInfo(@PathVariable Integer userId) {
        return AjaxResult.success(userService.getById(userId));
    }

    /**
     * 添加用户信息。
     *
     * @param user 用户信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Log(category = USER, operType = INSERT)
    public AjaxResult<Void> save(@Validated @RequestBody User user) {
        return toResult(userService.save(user));
    }

    /**
     * 更新用户信息。
     *
     * @param user 用户信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Log(category = USER, operType = UPDATE)
    public AjaxResult<Void> update(@Validated @RequestBody User user) {
        return toResult(userService.updateById(user));
    }

    /**
     * 根据用户主键删除用户信息。
     *
     * @param userId 用户主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{userId}")
    @Log(category = USER, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer userId) {
        return toResult(userService.removeById(userId));
    }

}