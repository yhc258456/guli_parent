package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author rachel
 * @since 2021-03-15
 */
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = eduChapterService.getChapterVideoById(courseId);
        return R.ok().data("list", list);
    }

    @PostMapping("addChater")
    public R addChater(@RequestBody EduChapter eduChapter) {
        eduChapterService.addChater(eduChapter);
        return R.ok();
    }

    @PostMapping("updateChater")
    public R updateChater(@RequestBody EduChapter eduChapter) {
        eduChapterService.updateChater(eduChapter);
        return R.ok();
    }

    @GetMapping("getChaterById/{chapterId}")
    public R getChaterById(@PathVariable String chapterId) {
        EduChapter e = eduChapterService.getChaterById(chapterId);
        return R.ok().data("eduChapter", e);
    }

    @DeleteMapping("deleteChaterById/{chapterId}")
    public R deleteChaterById(@PathVariable String chapterId) {
        boolean flag = eduChapterService.deleteChaterById(chapterId);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

