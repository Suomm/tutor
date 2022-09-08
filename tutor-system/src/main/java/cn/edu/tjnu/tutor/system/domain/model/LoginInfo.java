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
 * 用户登录信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@Builder
@IndexName
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 1713865713115996871L;

    /**
     * 登陆信息主键。
     */
    @IndexId
    private String infoId;

    /**
     * 用户编号。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String userCode;

    /**
     * 登录 IP 地址。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String ipaddr;

    /**
     * 用户状态。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String status;

    /**
     * 浏览器渲染引擎。
     */
    @IndexField(fieldType = FieldType.TEXT)
    private String engine;

    /**
     * 登录地点。
     */
    @IndexField(fieldType = FieldType.TEXT)
    private String address;

    /**
     * 浏览器类型。
     */
    @IndexField(fieldType = FieldType.TEXT)
    private String browser;

    /**
     * 操作系统。
     */
    @IndexField(fieldType = FieldType.TEXT)
    private String os;

    /**
     * 登录时间。
     */
    @IndexField(fieldType = FieldType.DATE, dateFormat = GlobalConst.DATE_TIME_FORMAT)
    private LocalDateTime loginTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof LoginInfo) {
            LoginInfo other = (LoginInfo) o;
            return Objects.equals(this.infoId, other.infoId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.infoId);
    }

}