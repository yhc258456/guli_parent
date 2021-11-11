package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.ordervo.CourseWebOrder;
import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.eduservice.client.EduClient;
import com.atguigu.eduservice.client.UcenterClient;
import com.atguigu.eduservice.entity.Order;
import com.atguigu.eduservice.mapper.OrderMapper;
import com.atguigu.eduservice.service.OrderService;
import com.atguigu.eduservice.utils.OrderNoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author rachel
 * @since 2021-03-31
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrder(String courseId, String memberId) {
        // 1.远程调用获取用户信息
        UcenterMemberOrder ucenterMember = ucenterClient.getInfo(memberId);

        // 2.远程调用获取课程信息
        CourseWebOrder courseDto = eduClient.getOrderCousrInfo(courseId);


        Order order = new Order();

        // 3.生成订单表
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        order.setTeacherName(courseDto.getTeacherName());
        order.setTotalFee(courseDto.getPrice());

        order.setMemberId(memberId);
        order.setMobile(ucenterMember.getMobile());
        order.setNickname(ucenterMember.getNickname());
        order.setStatus(0);
        order.setPayType(1); // 1代表微信支付 2代表支付宝支付
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
