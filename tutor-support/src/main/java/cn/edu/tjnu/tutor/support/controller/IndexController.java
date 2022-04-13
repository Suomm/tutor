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

import cn.edu.tjnu.tutor.common.core.controller.BaseController;
import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.system.domain.view.RouterVO;
import cn.edu.tjnu.tutor.system.domain.view.UserVO;
import cn.edu.tjnu.tutor.system.service.MenuService;
import cn.edu.tjnu.tutor.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_STUDENT;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_TEACHER;

/**
 * 首页控制器。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@Secured({ROLE_STUDENT, ROLE_TEACHER})
public class IndexController extends BaseController {

    private final MenuService menuService;
    private final UserService userService;

    /**
     * 后台首页展示内容。
     *
     * @return Hello World！
     */
    @GetMapping({"/", "/index"})
    public AjaxResult<String> index() {
        return success("Hello World!");
    }

    /**
     * 获取当前登录用户信息。
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public AjaxResult<UserVO> getInfo() {
        return success(userService.getInfo(getUserId()));
    }

    /**
     * 获取当前登录用户对应的路由信息。
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public AjaxResult<List<RouterVO>> getRouters() {
        return success(menuService.getRouters(getUserId()));
    }

}