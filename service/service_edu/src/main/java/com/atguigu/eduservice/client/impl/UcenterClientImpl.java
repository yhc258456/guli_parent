package com.atguigu.eduservice.client.impl;

import com.atguigu.eduservice.client.UcenterClient;
import com.atguigu.eduservice.entity.frontvo.UcenterMemberPay;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UcenterMemberPay getUcenterPay(String memberId) {
        return null;
    }
}
