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

import cn.easyes.annotation.IndexField;
import cn.easyes.annotation.IndexId;
import cn.easyes.annotation.IndexName;
import cn.easyes.common.enums.FieldType;
import cn.edu.tjnu.tutor.common.constant.GlobalConst;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 操作日志信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@Builder
@IndexName
public class OperLog implements Serializable {

    private static final long serialVersionUID = -3652454157461927553L;

    /**
     * 操作日志主键。
     */
    @IndexId
    private String operId;

    /**
     * 用户编号。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String userCode;

    /**
     * 操作类别。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String category;

    /**
     * 请求 IP 地址。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String operIp;

    /**
     * 请求路径。
     */
    @IndexField(fieldType = FieldType.TEXT)
    private String operUrl;

    /**
     * 操作类型。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String operType;

    /**
     * 请求参数。
     */
    @IndexField(fieldType = FieldType.TEXT)
    private String operParam;

    /**
     * 操作状态。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String operStatus;

    /**
     * 操作方法。
     */
    @IndexField(fieldType = FieldType.TEXT)
    private String method;

    /**
     * 请求方法。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String httpMethod;

    /**
     * 错误信息。
     */
    @IndexField(fieldType = FieldType.TEXT)
    private String errorMsg;

    /**
     * 返回结果。
     */
    @IndexField(fieldType = FieldType.TEXT)
    private String jsonResult;

    /**
     * 操作时间。
     */
    @IndexField(fieldType = FieldType.DATE, dateFormat = GlobalConst.DATE_TIME_FORMAT)
    private LocalDateTime operTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof OperLog) {
            OperLog other = (OperLog) o;
            return Objects.equals(this.operId, other.operId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.operId);
    }

}