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

package cn.edu.tjnu.tutor.common.core.controller;

import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.common.util.PageUtils;
import cn.edu.tjnu.tutor.common.util.SecurityUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.data.domain.Page;

/**
 * 基本控制器。
 *
 * @author 王帅
 * @since 2.0
 */
public abstract class BaseController {

    /**
     * 返回失败信息。
     */
    protected AjaxResult<Void> error(String msg) {
        return AjaxResult.error(msg);
    }

    /**
     * 返回成功数据。
     */
    protected <T> AjaxResult<T> success(T data) {
        return AjaxResult.success(data);
    }

    /**
     * 返回分页成功数据。
     */
    protected <T> AjaxResult<PageVO<T>> pageSuccess(Page<T> page) {
        return AjaxResult.success(PageUtils.convert(page));
    }

    /**
     * 返回分页成功数据。
     */
    protected <T> AjaxResult<PageVO<T>> pageSuccess(IPage<T> page) {
        return AjaxResult.success(PageUtils.convert(page));
    }

    /**
     * 响应返回结果。
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult<Void> toResult(int rows) {
        return rows > 0 ? AjaxResult.SUCCESS : AjaxResult.ERROR;
    }

    /**
     * 响应返回结果。
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult<Void> toResult(boolean result) {
        return result ? AjaxResult.SUCCESS : AjaxResult.ERROR;
    }

    /**
     * 获取当前登录用户主键。
     *
     * @return 用户主键
     */
    protected Integer getUserId() {
        return getLoginUser().getUserId();
    }

    /**
     * 获取用户缓存信息。
     *
     * @return 登录用户
     */
    protected LoginUser getLoginUser() {
        return SecurityUtils.getLoginUser();
    }

}
