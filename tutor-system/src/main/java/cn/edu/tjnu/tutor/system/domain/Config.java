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

package cn.edu.tjnu.tutor.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.io.Serializable;

/**
 * 参数配置。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("sys_config")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 参数主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer configId;

    /**
     * 参数名称。
     */
    private String configName;

    /**
     * 参数键名。
     */
    private String configKey;

    /**
     * 参数键值。
     */
    private String configValue;

}