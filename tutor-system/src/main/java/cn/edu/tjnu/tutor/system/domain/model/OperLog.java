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

package cn.edu.tjnu.tutor.system.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@Builder
@Document(indexName = "oper_log")
public class OperLog implements Serializable {

    private static final long serialVersionUID = -3652454157461927553L;

    /**
     * 操作日志主键。
     */
    @Id
    private Long operId;

    /**
     * 用户编号。
     */
    @Field(type = FieldType.Keyword)
    private String userCode;

    /**
     * 操作类别。
     */
    @Field(type = FieldType.Keyword)
    private String category;

    /**
     * 请求 IP 地址。
     */
    @Field(type = FieldType.Keyword)
    private String operIp;

    /**
     * 请求路径。
     */
    private String operUrl;

    /**
     * 操作类型。
     */
    @Field(type = FieldType.Keyword)
    private String operType;

    /**
     * 请求参数。
     */
    private String operParam;

    /**
     * 操作状态。
     */
    @Field(type = FieldType.Keyword)
    private String operStatus;

    /**
     * 操作方法。
     */
    private String method;

    /**
     * 请求方法。
     */
    @Field(type = FieldType.Keyword)
    private String httpMethod;

    /**
     * 错误信息。
     */
    private String errorMsg;

    /**
     * 返回结果。
     */
    private String jsonResult;

    /**
     * 操作时间。
     */
    private LocalDateTime operTime;

}