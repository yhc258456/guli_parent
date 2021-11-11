package com.atguigu.msm.service;

import java.util.HashMap;

public interface MsmService {
    boolean sengMessage(HashMap<String, Object> params,String phoneNum);
}
