package com.atguigu.cmsservice.controller;


import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author rachel
 * @since 2021-03-28
 */
@RestController
@RequestMapping("/cmsservice/banneradmin")
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("pageBanner/{current}/{limit}")
    public R getpageBanner(@PathVariable long current, @PathVariable long limit) {
        Page<CrmBanner> bannerPage = new Page<>(current, limit);
        IPage<CrmBanner> page = crmBannerService.page(bannerPage, null);
        List<CrmBanner> list = page.getRecords();
        long total = page.getTotal();
        return R.ok().data("list", list).data("total", total);
    }

    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);
        return R.ok();
    }

    @DeleteMapping("delete/{id}")
    public R delelteBannerById(@PathVariable String id) {
        crmBannerService.removeById(id);
        return R.ok();
    }

    @PostMapping("updateBanner")
    public R updateBanner(@RequestBody CrmBanner crmBanner) {
        crmBannerService.updateById(crmBanner);
        return R.ok();
    }

    @GetMapping("pageBanner/{id}")
    public R getBannerById(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return R.ok().data("banner", banner);
    }

}

