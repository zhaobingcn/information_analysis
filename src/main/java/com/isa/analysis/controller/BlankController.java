package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hexu on 2016/12/7.
 */
@Controller
public class BlankController {

    @RequestMapping(value = "/blank")
    public String blank(){
        return "blank";
    }
}
