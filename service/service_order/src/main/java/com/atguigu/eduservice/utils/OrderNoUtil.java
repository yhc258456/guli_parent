package com.atguigu.eduservice.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNoUtil {
    /**
     * 获取订单号
     *
     * @return
     */
    public static String getOrderNo() {
        Random random = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            result = result.append(random.nextInt(10));
        }
        return newDate + result;
    }
}
