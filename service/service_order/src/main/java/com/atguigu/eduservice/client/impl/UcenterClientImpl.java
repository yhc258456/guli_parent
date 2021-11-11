package com.atguigu.eduservice.client.impl;

import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.eduservice.client.UcenterClient;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UcenterMemberOrder getInfo(String memberId) {
        return null;
    }
}
