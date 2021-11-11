package com.atguigu.cmsservice.service.impl;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.mapper.CrmBannerMapper;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author rachel
 * @since 2021-03-28
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
//    @Cacheable(value = "banner", key = "'selectIndexList'")
    public List<CrmBanner> selectAllBanner() {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.select().last("limit 4");
        return baseMapper.selectList(queryWrapper);
    }
}
