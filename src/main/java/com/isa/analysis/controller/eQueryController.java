package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hexu on 2016/12/7.
 */
@Controller
public class eQueryController {

    @RequestMapping(value = "/expertQuery")
    public String expertQuery(){
        return "expertQuery";
    }
}
