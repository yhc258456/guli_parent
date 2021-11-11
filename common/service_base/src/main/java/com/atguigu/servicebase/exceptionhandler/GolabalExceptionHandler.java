package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GolabalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("系统错误");
    }

    @ExceptionHandler(ArithmeticException.class)
    public R error2(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常处理");
    }

    @ExceptionHandler(GuliException.class)
    public R error3(GuliException e) {
        log.error(e.getMsg()); // 将会被写到error文件中去
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }


}
