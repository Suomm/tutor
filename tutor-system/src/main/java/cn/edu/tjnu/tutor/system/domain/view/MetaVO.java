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

package cn.edu.tjnu.tutor.system.domain.view;

import lombok.Data;

import java.io.Serializable;

/**
 * 路由元数据信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class MetaVO implements Serializable {

    private static final long serialVersionUID = -8923807398745375358L;

    /**
     * 菜单图标。
     */
    private String icon;

    /**
     * 菜单标题。
     */
    private String title;

    /**
     * 菜单排序，只对第一级有效。
     */
    private Integer orderNo;

}