package com.isa.analysis.controller;

import com.isa.analysis.neo4jkernel.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by hexu on 2016/11/1.
 */
@RestController
public class GraphController {

    @Autowired
    private GraphService graphService;

    @RequestMapping(value = "/graph")
    public Map<String, Object> graph(@RequestParam(value = "limit", required = false)Integer limit){
//        System.out.println("qqqqq");
//        System.out.println(limit);
        graphService.graph(limit);
        return null;
    }
}
