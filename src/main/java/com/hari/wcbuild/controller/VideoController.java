package com.hari.wcbuild.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hari.wcbuild.domain.Video;
import com.hari.wcbuild.mapper.VideoMapper;
import com.hari.wcbuild.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ResponseBody
    @RequestMapping("test")
    public String test(){
        return "hello world222";
    }

    @ResponseBody
    @RequestMapping("videolist")
    public Object videolist(){
        return videoService.findAll();
    }

    /**
     * 分页接口
     * @param page 当前第几页，默认是第一页
     * @param size  每页显示几条
     * @return
     */
    @GetMapping("page")
    public Object pageVideo(@RequestParam(value = "page",defaultValue = "1")int page,
                            @RequestParam(value = "size",defaultValue = "10")int size){
        PageHelper.startPage(page,size);
        List<Video> list = videoService.findAll();
        PageInfo<Video> pageInfo = new PageInfo<>(list);
        Map<String,Object> data = new HashMap<>();
        data.put("total_size", pageInfo.getTotal());
        data.put("total_page", pageInfo.getPages());
        data.put("current_page", page);
        data.put("data", pageInfo.getList());

        return data;
    }


}
