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

package cn.edu.tjnu.tutor.controller;

import cn.edu.tjnu.tutor.common.annotation.Log;
import cn.edu.tjnu.tutor.common.constant.RedisConsts;
import cn.edu.tjnu.tutor.common.core.controller.BaseController;
import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.util.RedisUtils;
import cn.edu.tjnu.tutor.system.domain.entity.Role;
import cn.edu.tjnu.tutor.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 2021/11/24
 *
 * @author 王帅
 * @since 1.0
 */
@RestController
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Log
    @GetMapping("/getAllRoles")
    public AjaxResult<List<Role>> test() {
        RedisUtils.setCacheObject(RedisConsts.TEST_KEY, "test redis");
        return AjaxResult.success(roleService.getAllRoles());
    }

}
