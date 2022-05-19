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

package cn.edu.tjnu.tutor.system.domain.meta;

import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * <p>参数配置元数据信息，用于后台操作数据。
 *
 * <p>插入参数配置信息时：
 * <blockquote><pre>
 *     {
 *         "configName": "测试参数",
 *         "configKey": "test",
 *         "configValue": "123456"
 *     }
 * </pre></blockquote>
 *
 * <p>更新参数配置信息时：
 * <blockquote><pre>
 *     {
 *          "configId": 1,
 *          "configName": "测试参数",
 *          "configValue": "123456"
 *      }
 * </pre></blockquote>
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class ConfigMeta implements Serializable {

    private static final long serialVersionUID = -8809014105413614348L;

    /**
     * 参数主键。
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private Integer configId;

    /**
     * 参数名称。
     */
    @Length(max = 100)
    @NotNull(groups = Insert.class)
    private String configName;

    /**
     * 参数键名。
     */
    @Length(max = 100)
    @Null(groups = Update.class)
    @NotNull(groups = Insert.class)
    private String configKey;

    /**
     * 参数键值。
     */
    @Length(max = 500)
    @NotNull(groups = Insert.class)
    private String configValue;

}