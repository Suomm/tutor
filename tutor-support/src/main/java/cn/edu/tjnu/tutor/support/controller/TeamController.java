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
import cn.edu.tjnu.tutor.common.core.domain.dto.PageDTO;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.system.domain.entity.Team;
import cn.edu.tjnu.tutor.system.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.enums.Category.TEAM;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 导师团信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/team")
public class TeamController extends BaseController {

    private final TeamService teamService;

    /**
     * 分页查询导师团信息。
     *
     * @param pageDTO 分页参数
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<PageVO<Team>> list(PageDTO pageDTO) {
        return pageSuccess(teamService.page(pageDTO.page()));
    }

    /**
     * 根据导师团主键获取详细信息。
     *
     * @param teamId 导师团主键
     * @return 导师团信息详情
     */
    @GetMapping("getInfo/{teamId}")
    public AjaxResult<Team> getInfo(@PathVariable Integer teamId) {
        return success(teamService.getById(teamId));
    }

    /**
     * 添加导师团信息。
     *
     * @param team 导师团信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Log(category = TEAM, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody Team team) {
        return toResult(teamService.save(team));
    }

    /**
     * 更新导师团信息。
     *
     * @param team 导师团信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Log(category = TEAM, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody Team team) {
        return toResult(teamService.updateById(team));
    }

    /**
     * 根据导师团主键删除导师团信息。
     *
     * @param teamId 导师团主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{teamId}")
    @Log(category = TEAM, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer teamId) {
        return toResult(teamService.removeById(teamId));
    }

}