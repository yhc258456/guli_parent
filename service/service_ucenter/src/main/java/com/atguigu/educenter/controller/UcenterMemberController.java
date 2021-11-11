package com.atguigu.educenter.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.LoginVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author rachel
 * @since 2021-03-29
 */
@RestController
@RequestMapping("/educenter/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    // 登录
    @PostMapping("login")
    public R userLogin(@RequestBody LoginVo loginVo) {
        // 调用service 返回token
        String token = ucenterMemberService.login(loginVo);
        return R.ok().data("_token", token).message("登录成功");
    }

    // 注册
    @PostMapping("register")
    public R userRegister(@RequestBody RegisterVo registerVo) {
        // 调用service 返回token
        ucenterMemberService.register(registerVo);
        return R.ok().message("注册成功");
    }

    // 根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        String id = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = ucenterMemberService.getById(id);
        return R.ok().data("item", member);
    }

    //根据用户id获取用户信息
    @PostMapping("getInfoUc/{memberId}")
    public UcenterMemberOrder getInfo(@PathVariable String memberId) {
        UcenterMember ucenterMember = ucenterMemberService.getById(memberId);
        if (null == ucenterMember) {
            throw new GuliException(28004, "用户未登录");
        }
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember, ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    //查询某一天注册人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day) {
        Integer count = ucenterMemberService.countRegisterDay(day);
        return R.ok().data("countRegister", count);
    }

}

