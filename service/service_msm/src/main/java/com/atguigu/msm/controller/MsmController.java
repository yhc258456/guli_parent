package com.atguigu.msm.controller;

import com.atguigu.commonutils.R;
import com.atguigu.msm.service.MsmService;
import com.atguigu.msm.utlis.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/msmservice/msm")
public class MsmController {


    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("sendMsg/{phoneNum}")
    public R sendMessage(@PathVariable String phoneNum) {
        // 从redis中获取短信验证码
        String code = redisTemplate.opsForValue().get(phoneNum);
        if (!StringUtils.isEmpty(code)) return R.ok();

        code = RandomUtil.getFourBitRandom();
        HashMap<String, Object> params = new HashMap<>();
        params.put("code", code);
        boolean flag = msmService.sengMessage(params, phoneNum);
        if (flag) {
            // 成功发送 将验证码发存到redis
            redisTemplate.opsForValue().set(phoneNum, code, 5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error();
        }
    }
}
