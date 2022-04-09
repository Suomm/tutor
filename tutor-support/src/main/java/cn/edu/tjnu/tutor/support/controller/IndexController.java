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

import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.system.domain.vo.RouterVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 首页控制器。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
public class IndexController {

    /**
     * 后台首页展示内容。
     *
     * @return Hello World！
     */
    @GetMapping({"/", "/index"})
    public AjaxResult<String> index() {
        return AjaxResult.success("Hello World!");
    }

    /**
     * 获取当前登录用户信息。
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public AjaxResult<Map<String, Object>> getInfo() {
        return AjaxResult.success(new HashMap<>(0));
    }

    /**
     * 获取路由信息。
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public AjaxResult<RouterVo> getRouters() {
        return AjaxResult.success(new RouterVo());
    }


}