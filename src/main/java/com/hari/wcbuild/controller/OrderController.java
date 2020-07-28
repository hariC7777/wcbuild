package com.hari.wcbuild.controller;

import com.hari.wcbuild.domain.JsonData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {

    @GetMapping("add")
    public JsonData saveOrder(){
        return JsonData.buildSuccess("下单成功");
    }


}
