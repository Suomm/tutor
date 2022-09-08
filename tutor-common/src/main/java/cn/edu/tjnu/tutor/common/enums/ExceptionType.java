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

package cn.edu.tjnu.tutor.common.enums;

import cn.edu.tjnu.tutor.common.locale.Localizable;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 异常类型枚举类。
 *
 * @author 王帅
 * @since 2.0
 */
public enum ExceptionType implements Localizable {

    // 所有异常类型及其描述信息

    COLLEGE_NOT_REGISTER("您所在的学院未注册！"),
    COLLEGE_NOT_EXISTS("您所选择的学院不存在！"),
    COLLEGE_CODE_ALREADY_EXISTS("学院编号 “{0}” 已经存在！"),
    COLLEGE_NAME_ALREADY_EXISTS("学院名称 “{0}” 已经存在！"),
    CONFIG_NAME_ALREADY_EXISTS("参数配置键值 “{0}” 已经存在！"),
    CLASS_NAME_ALREADY_EXISTS("班级名称 “{0}” 已经存在！"),
    EXCEL_EXPORT_FAILED("导出文档 “{0}.xlsx” 失败！"),
    EXCEL_IMPORT_FAILED("导入成功 {0} 条数据，失败 {1} 条数据，发生错误行号：{2}！"),
    EXCEL_FILE_OPEN_FAILED("打开 Excel 文档 “{0}” 失败！"),
    FILE_NOT_FOUND("文件 “{0}” 不存在！"),
    GROUP_NAME_ALREADY_EXISTS("小组名称 “{0}” 已经存在！"),
    MAJOR_NOT_EXISTS("您所选择的专业不存在！"),
    MAJOR_NAME_ALREADY_EXISTS("专业名称 “{0}” 已经存在！"),
    MENU_ALREADY_BIND_ROLE("菜单已绑定角色，不能删除！"),
    MENU_NAME_ALREADY_EXISTS("菜单名称 “{0}” 已经存在，请换用其他名称！"),
    RECORD_ALREADY_SCORED("课堂教学记录已经被打分！"),
    SUBMENU_ALREADY_EXISTS("存在子菜单，请先删除子菜单！"),
    USERS_ARE_NOT_IN_THE_SAME_GROUP("两个用户不在同一个分组内！"),
    USER_ARE_NOT_OWNED_GROUP("这个小组不是您所有的！"),
    USER_NAME_ALREADY_EXISTS("用户名称 “{0}” 已经存在！");

    private static final Map<Locale, ResourceBundle> CACHED_RESOURCE_BUNDLES =
            new ConcurrentHashMap<>();

    /**
     * 默认消息。
     */
    private final String sourceFormat;

    ExceptionType(String sourceFormat) {
        this.sourceFormat = sourceFormat;
    }

    @Override
    public String getLocalizedMessage(Locale locale, Object... args) {
        String message = null;
        try {
            ResourceBundle bundle = CACHED_RESOURCE_BUNDLES.get(locale);
            // 缓存 ResourceBundle 类
            if (bundle == null) {
                bundle = ResourceBundle.getBundle("ExceptionMessages", locale);
                CACHED_RESOURCE_BUNDLES.put(locale, bundle);
            }
            message = bundle.getString(name());
        } catch (MissingResourceException mre) {
            // do nothing here.
        }
        return MessageFormat.format(message == null ? sourceFormat : message, args);
    }

}