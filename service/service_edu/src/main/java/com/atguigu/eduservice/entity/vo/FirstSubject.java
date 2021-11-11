package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FirstSubject {

    private String id;
    private String title;

    //一个一级分类有多个二级分类
    private List<SecondSubject> children = new ArrayList<>();
}
