package com.atguigu.eduservice.client.impl;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodClientImpl implements VodClient {
    @Override
    public R romeveAliCoundVideo(String id) {
        return R.error().message("删除视频出错");
    }

    @Override
    public R batchDelete(List<String> videoList) {
        return R.error().message("删除多个视频出错");
    }
}
