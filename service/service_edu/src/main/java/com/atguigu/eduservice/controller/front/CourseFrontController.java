package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.CourseWebOrder;
import com.atguigu.eduservice.client.OrderClient;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.frontvo.CourseQueryFrontVo;
import com.atguigu.eduservice.entity.frontvo.CourseWebVo;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private OrderClient orderClient;

    // 前台返回课程列表
    @PostMapping("getFrontCousrList/{current}/{limit}")
    public R getFrontTeacherList(@PathVariable long current, @PathVariable long limit,
                                 @RequestBody CourseQueryFrontVo courseQueryVo) {
        Page<EduCourse> pageTeacher = new Page(current, limit);
        Map<String, Object> map = eduCourseService.getFrontCourseList(pageTeacher, courseQueryVo);
        return R.ok().data(map);
    }


    // 前台返回课程详细信息isbuyCourse
    @GetMapping("getFrontCousrInfo/{courseId}")
    public R getFrontCousrInfo(@PathVariable String courseId, HttpServletRequest request) {
        // 1.根据课程id，多表查询课程基本信息
        CourseWebVo courseWeb = eduCourseService.getFrontCourseInfo(courseId);

        // 2.查询课程章节小节
        List<ChapterVo> chapterVideolist = eduChapterService.getChapterVideoById(courseId);

        // 3.查询用户是否已经购买该课程
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (!StringUtils.isEmpty(memberId)) {
            boolean buyCourse = orderClient.isBuyCourse(courseId, memberId);
            return R.ok().data("courseWeb", courseWeb).data("chapterVideolist", chapterVideolist).data("isbuyCourse", buyCourse);
        }
        return R.ok().data("courseWeb", courseWeb).data("chapterVideolist", chapterVideolist);

    }

    // 根据id查询课程
    @PostMapping("getOrderCousrInfo/{courseId}")
    public CourseWebOrder getOrderCousrInfo(@PathVariable String courseId) {
        // 1.根据课程id，多表查询课程基本信息
        CourseWebVo courseWeb = eduCourseService.getFrontCourseInfo(courseId);
        CourseWebOrder courseWebOrder = new CourseWebOrder();
        BeanUtils.copyProperties(courseWeb, courseWebOrder);
        return courseWebOrder;
    }

    @PostMapping("getCourseByName")
    public R getCourseByName(String courseName) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", courseName);
        List<EduCourse> list = eduCourseService.list(queryWrapper);
        return R.ok().data("list", list);
    }

}
