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

package cn.edu.tjnu.tutor.system.service.impl;

import cn.edu.tjnu.tutor.common.util.RedisUtils;
import cn.edu.tjnu.tutor.common.util.SqlUtils;
import cn.edu.tjnu.tutor.system.domain.entity.Config;
import cn.edu.tjnu.tutor.system.mapper.ConfigMapper;
import cn.edu.tjnu.tutor.system.service.ConfigService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static cn.edu.tjnu.tutor.common.constant.RedisConst.CONFIG_MAP_NAME;

/**
 * 参数配置服务层实现。
 *
 * @author 王帅
 * @since 2.0
 */
@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    private final ConfigMapper configMapper;

    @Override
    public <E extends IPage<Config>> E page(E page) {
        return configMapper.selectPage(page, Wrappers.emptyWrapper());
    }

    @Override
    public boolean save(Config config) {
        int rows = configMapper.insert(config);
        if (rows > 0) {
            RedisUtils.setCacheMap(CONFIG_MAP_NAME, config.getConfigKey(), config.getConfigValue());
        }
        return SqlUtils.toBool(rows);
    }

    @Override
    public boolean updateById(Config config) {
        int rows = configMapper.updateById(config);
        if (rows > 0) {
            RedisUtils.setCacheMap(CONFIG_MAP_NAME, config.getConfigKey(), config.getConfigValue());
        }
        return SqlUtils.toBool(rows);
    }

    @Override
    public boolean removeById(Integer configId) {
        Config config = configMapper.selectById(configId);
        if (config == null) {
            return false;
        }
        int rows = configMapper.deleteById(configId);
        if (rows > 0) {
            RedisUtils.delCacheMap(CONFIG_MAP_NAME, config.getConfigKey());
        }
        return SqlUtils.toBool(rows);
    }

    @Override
    public void loadConfigCache() {
        configMapper.selectList(Wrappers.emptyWrapper()).forEach(e ->
                RedisUtils.setCacheMap(CONFIG_MAP_NAME, e.getConfigKey(), e.getConfigValue()));
    }

    @Override
    public void deleteConfigCache() {
        RedisUtils.deleteMap(CONFIG_MAP_NAME);
    }

}