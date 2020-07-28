package com.hari.wcbuild.controller.admin;

import com.hari.wcbuild.domain.Video;
import com.hari.wcbuild.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    /**
     * 根据id删除视频
     * @param videoId
     * @return
     */
    @DeleteMapping("del")
    public Object delById(@RequestParam(value = "video_id",required = true)int videoId){
        return videoService.delete(videoId);
    }

    /**
     * 根据id更新视频
     * @return
     */
    @PutMapping("update")
    public Object update(@RequestBody Video video){
        return videoService.update(video);
    }


    /**
     * 保存视频对象
     * @param video
     * @return
     */
    @PostMapping("save")
    public Object save(@RequestBody Video video){
        return videoService.save(video);
    }

}
