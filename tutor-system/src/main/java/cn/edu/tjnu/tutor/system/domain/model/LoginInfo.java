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

import cn.edu.tjnu.tutor.common.core.domain.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@Document(indexName = "login_info")
public class LoginInfo extends BaseEntity {

    private static final long serialVersionUID = 1713865713115996871L;

    /**
     * 登陆信息主键。
     */
    @Id
    private String infoId;

    /**
     * 用户编号。
     */
    @Field(type = FieldType.Keyword)
    private String userCode;

    /**
     * 登录 IP 地址。
     */
    @Field(type = FieldType.Keyword)
    private String ipaddr;

    /**
     * 用户状态。
     */
    @Field(type = FieldType.Keyword)
    private String status;

    /**
     * 浏览器渲染引擎。
     */
    private String engine;

    /**
     * 登录地点。
     */
    private String address;

    /**
     * 浏览器类型。
     */
    private String browser;

    /**
     * 操作系统。
     */
    private String os;

    /**
     * 登录时间。
     */
    @Field(format = DateFormat.date_time_no_millis)
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