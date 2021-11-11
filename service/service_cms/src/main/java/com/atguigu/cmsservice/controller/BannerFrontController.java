package com.atguigu.cmsservice.controller;


import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * 查询热门课程和讲师的街接口
 * </p>
 *
 * @author rachel
 * @since 2021-03-28
 */
@RestController
@RequestMapping("/cmsservice/bannerfront")
public class BannerFrontController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> bannerList = crmBannerService.selectAllBanner();
        return R.ok().data("bannerList", bannerList);
    }
}

