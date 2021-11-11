package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author rachel
 * @since 2021-03-31
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courseId, String memberId);
}
