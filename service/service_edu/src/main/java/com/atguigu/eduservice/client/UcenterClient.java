package com.atguigu.eduservice.client;

import com.atguigu.eduservice.client.impl.UcenterClientImpl;
import com.atguigu.eduservice.entity.frontvo.UcenterMemberPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "service-ucenter", fallback = UcenterClientImpl.class)
@Component
public interface UcenterClient {
    //根据用户id获取用户信息
    @PostMapping("/educenter/member/getInfoUc/{memberId}")
    UcenterMemberPay getUcenterPay(@PathVariable("memberId") String memberId);
}
