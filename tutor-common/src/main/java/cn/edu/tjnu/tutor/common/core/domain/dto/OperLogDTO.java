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

package cn.edu.tjnu.tutor.common.core.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用操作日志实体。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class OperLogDTO implements Serializable {

    private static final long serialVersionUID = -3241117186339249582L;

    /**
     * 操作模块。
     */
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）。
     */
    private Integer operType;

    /**
     * 业务类型数组。
     */
    private Integer[] businessTypes;

    /**
     * 请求方法。
     */
    private String method;

    /**
     * 请求方式。
     */
    private String requestMethod;

    /**
     * 操作人员 ID。
     */
    private String operId;

    /**
     * 请求 URL。
     */
    private String operUrl;

    /**
     * 操作地址。
     */
    private String operIp;

    /**
     * 操作地点。
     */
    private String operLocation;

    /**
     * 请求参数。
     */
    private String operParam;

    /**
     * 返回参数。
     */
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）。
     */
    private Integer status;

    /**
     * 错误消息。
     */
    private String errorMsg;

    /**
     * 操作时间。
     */
    private Date operTime;

}