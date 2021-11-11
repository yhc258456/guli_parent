package com.atguigu.eduservice.client.impl;

import com.atguigu.commonutils.ordervo.CourseWebOrder;
import com.atguigu.eduservice.client.EduClient;
import org.springframework.stereotype.Component;

@Component
public class EduClientImpl implements EduClient {
    @Override
    public CourseWebOrder getOrderCousrInfo(String courseId) {
        return null;
    }
}
