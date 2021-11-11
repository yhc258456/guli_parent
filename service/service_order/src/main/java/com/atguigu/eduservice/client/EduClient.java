package com.atguigu.eduservice.client;

import com.atguigu.commonutils.ordervo.CourseWebOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-edu")
public interface EduClient {
    // 根据id查询课程
    @PostMapping("/eduservice/coursefront/getOrderCousrInfo/{courseId}")
    CourseWebOrder getOrderCousrInfo(@PathVariable String courseId);
}

