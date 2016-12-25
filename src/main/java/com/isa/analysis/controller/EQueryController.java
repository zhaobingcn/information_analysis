package com.isa.analysis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by hexu on 2016/12/7.
 */
@Controller
public class EQueryController {

    @RequestMapping(value = "/queryOfExpert")
    public String expertQuery(){
        return "queryOfExpert";
    }

    @RequestMapping(value = "/queryOfExpert/queryAuthors")
    public @ResponseBody Map<String, Object> queryAuthors(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "institution", required = false)String institution
    ){
        
        return null;
    }
}
