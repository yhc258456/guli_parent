package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-vod", fallback = VodClientImpl.class)
@Component
public interface VodClient {

    // 定义调用的路径
    @DeleteMapping("/eduvod/video/romeveAliCoundVideo/{id}")
    R romeveAliCoundVideo(@PathVariable("id") String id);

    // 定义调用的路径
    @DeleteMapping("/eduvod/video/delete-batch")
    R batchDelete(@RequestParam("videoList") List<String> videoList);
}
