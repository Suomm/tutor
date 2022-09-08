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

package cn.edu.tjnu.tutor.system.service;

import cn.edu.tjnu.tutor.system.domain.dto.MarkDTO;
import cn.edu.tjnu.tutor.system.domain.entity.InternInfo;
import cn.edu.tjnu.tutor.system.domain.view.*;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 教育实习基本信息服务层。
 *
 * @author 王帅
 * @since 2.0
 */
public interface InternInfoService extends IService<InternInfo> {

    /**
     * 听课记录。
     */
    String TK = "TK";

    /**
     * 实习教案。
     */
    String JA = "JA";

    /**
     * 教研活动。
     */
    String JY = "JY";

    /**
     * 师德表现。
     */
    String SD = "SD";

    /**
     * 个人总结。
     */
    String GR = "GR";

    /**
     * 中学教师。
     */
    String ZX = "ZX";

    /**
     * 高校教师。
     */
    String GX = "GX";

    /**
     * 获取实习生基本信息。
     *
     * @param userId 实习生主键
     * @return 实习生基本信息
     */
    InternInfoVO getInfo(Integer userId);

    /**
     * 获取实习生成绩信息。
     *
     * @param userId 实习生主键
     * @return 成绩报告
     */
    ReportVO getReport(Integer userId);

    /**
     * 得到分数
     *
     * @param userId 用户id
     * @param type   类型
     * @return {@link ScoreVO}
     */
    ScoreVO getScore(Integer userId, String type);

    /**
     * 组得分
     *
     * @param userId  用户id
     * @param type    类型
     * @param markDTO 马克dto
     * @return boolean
     */
    boolean setScore(Integer userId, String type, MarkDTO markDTO);

    /**
     * 设置评论
     *
     * @param userId  用户id
     * @param type    类型
     * @param content 内容
     * @return boolean
     */
    boolean setComment(Integer userId, String type, String content);

    /**
     * 得到评论
     *
     * @param userId 用户id
     * @return {@link RemarkVO}
     */
    RemarkVO getRemark(Integer userId);

    /**
     * 得到组得分
     *
     * @param userId 用户id
     * @return {@link GroupScoreVO}
     */
    GroupScoreVO getGroupScore(Integer userId);

}