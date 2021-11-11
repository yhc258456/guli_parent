package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/eduservice/user")
public class EduLoginController {

    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "12138");
    }

    @GetMapping("info")
    public R getInfo() {
        return R.ok().data("roles", "java攻城狮").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif").data("name", "rachel");
    }
}
