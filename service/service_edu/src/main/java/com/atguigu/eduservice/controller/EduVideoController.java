package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author rachel
 * @since 2021-03-15
 */
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;


    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    // TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        EduVideo eduVideo = eduVideoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)) {
            R r = vodClient.romeveAliCoundVideo(videoSourceId);
            if (r.getCode()==20001) throw new GuliException(20001,"远程服务响应超时");
        }
        eduVideoService.removeById(id);
        return R.ok();
    }

    //修改小节 TODO
    @PostMapping("updateById")
    public R updateVideoById(@RequestBody EduVideo eduVideo) {
        boolean b = eduVideoService.updateById(eduVideo);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 根据id查询小节 TODO
    @GetMapping("getByVideoId/{videoId}")
    public R getByVideoId(@PathVariable String videoId) {
        EduVideo video = eduVideoService.getById(videoId);
        return R.ok().data("video", video);
    }


}

