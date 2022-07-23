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
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.common.util.ExcelUtils;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import cn.edu.tjnu.tutor.system.domain.entity.User;
import cn.edu.tjnu.tutor.system.domain.meta.UserMeta;
import cn.edu.tjnu.tutor.system.domain.query.UserQuery;
import cn.edu.tjnu.tutor.system.domain.view.UserVO;
import cn.edu.tjnu.tutor.system.service.UserService;
import cn.edu.tjnu.tutor.system.structure.UserStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_ADMIN;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_ROOT;
import static cn.edu.tjnu.tutor.common.enums.Category.COLLEGE;
import static cn.edu.tjnu.tutor.common.enums.Category.USER;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.USER_NAME_ALREADY_EXISTS;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 用户信息控制器。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserStruct userStruct;
    private final UserService userService;

    /**
     * 查询用户信息。
     *
     * @param userQuery 查询参数
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<PageVO<UserVO>> list(@Validated UserQuery userQuery) {
        return pageSuccess(userService.pageVO(userQuery));
    }

    /**
     * 查询菜单绑定的角色主键。
     *
     * @param userId 菜单主键|1
     * @return 角色主键
     */
    @GetMapping("roleIdList/{userId}")
    public AjaxResult<List<Integer>> roleIdList(@PathVariable Integer userId) {
        return success(userService.roleIdList(userId));
    }

    /**
     * 添加用户信息。
     *
     * @param userMeta 用户信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Log(category = USER, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) UserMeta userMeta) {
        User user = userStruct.toEntity(userMeta);
        if (!userService.containsUser(user.getUserCode())) {
            return error(USER_NAME_ALREADY_EXISTS, user.getUserName());
        }
        return toResult(userService.saveAndBind(user));
    }

    /**
     * 更新用户信息。
     *
     * @param userMeta 用户信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @PutMapping("update")
    @Log(category = USER, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) UserMeta userMeta) {
        return toResult(userService.updateAndBind(userStruct.toEntity(userMeta)));
    }

    /**
     * 导入 Excel 文档中的数据到数据库。
     *
     * @param file Excel 文档
     * @return {@code code = 200} 导入成功，{@code code = 500} 导入失败
     */
    @PostMapping("importData")
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    @Log(category = COLLEGE, operType = IMPORT)
    public AjaxResult<Void> importData(MultipartFile file) {
        return toResult(ExcelUtils.readExcel(file, userService));
    }

    /**
     * 导出数据到 Excel 文档。
     *
     * @param response 响应对象
     */
    @Secured(ROLE_ROOT)
    @GetMapping("exportData")
    public void exportData(UserQuery userQuery, HttpServletResponse response) {
        ExcelUtils.writeExcel(response, "用户信息汇总", userService, userQuery);
    }

    /**
     * 导出 Excel 模板。
     *
     * @param response 响应对象
     */
    @GetMapping("exportTmpl")
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    public void exportTmpl(HttpServletResponse response) {
        ExcelUtils.writeTemplate(response, "用户信息模板", userService);
    }

}