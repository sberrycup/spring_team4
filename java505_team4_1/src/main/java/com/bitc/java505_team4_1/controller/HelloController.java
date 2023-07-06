package com.bitc.java505_team4_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HelloController {

    @RequestMapping("index")
    public String index() throws Exception{
        return "index";
    }
}
