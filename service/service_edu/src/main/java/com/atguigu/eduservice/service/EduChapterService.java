package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author rachel
 * @since 2021-03-15
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoById(String courseId);

    void addChater(EduChapter eduChapter);

    void updateChater(EduChapter eduChapter);

    EduChapter getChaterById(String chapterId);

   boolean deleteChaterById(String chapterId);

    void removeChapterByCourseId(String courseId);
}
