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

package cn.edu.tjnu.tutor.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * 校验工具类。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidUtils {

    private static final Validator VALIDATOR = SpringUtils.getBean(Validator.class);

    /**
     * 使用 {@link Validator} 对数据进行校验。
     *
     * @param object 需要校验的对象
     * @param <T> 数据类型
     * @throws ConstraintViolationException 如果校验失败，则抛出该异常
     */
    public static <T> void validate(T object) throws ConstraintViolationException {
        Set<ConstraintViolation<T>> violationSet = VALIDATOR.validate(object);
        if (!violationSet.isEmpty()) {
            throw new ConstraintViolationException(violationSet);
        }
    }

}