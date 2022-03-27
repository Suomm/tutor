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

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.tjnu.tutor.system.domain.Oss;
import cn.edu.tjnu.tutor.system.service.OssService;
import cn.edu.tjnu.tutor.system.mapper.OssMapper;
import org.springframework.stereotype.Service;

/**
 * 对象存信息储服务层实现。
 *
 * @author 王帅
 * @since 2.0
 */
@Service
public class OssServiceImpl extends ServiceImpl<OssMapper, Oss> implements OssService {
}