package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author rachel
 * @since 2021-03-07
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getFrontTeacherList(Page<EduTeacher> pageTeacher);
}
