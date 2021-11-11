package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService eduSubjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (null == subjectData) {
            throw new GuliException(20001, "文件数据为空");
        }
        String firstSubjectName = subjectData.getOneSubjectName();
        // 逐行读取
        EduSubject firstSubject = this.isExistFirstSubject(firstSubjectName, eduSubjectService);
        if (null == firstSubject) {
            firstSubject = new EduSubject();
            firstSubject.setParentId("0");
            firstSubject.setTitle(firstSubjectName);
            eduSubjectService.save(firstSubject);
        }

        String pid = firstSubject.getId();
        String secondSubjectName = subjectData.getTwoSubjectName();
        EduSubject existSecondSubject = this.isExistSecondSubject(secondSubjectName, pid, eduSubjectService);
        if (null == existSecondSubject) {
            EduSubject eduSubject = new EduSubject();
            eduSubject.setParentId(pid);
            eduSubject.setTitle(secondSubjectName);
            eduSubjectService.save(eduSubject);
        }


    }

    // 判断一级分类 不能重复
    private EduSubject isExistFirstSubject(String name, EduSubjectService eduSubjectService) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.eq("parent_id", "0");
        EduSubject one = eduSubjectService.getOne(queryWrapper);
        return one;
    }

    // 判断二级分类 不能重复
    private EduSubject isExistSecondSubject(String name, String pid, EduSubjectService eduSubjectService) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name);
        queryWrapper.eq("parent_id", pid);
        EduSubject one = eduSubjectService.getOne(queryWrapper);
        return one;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
