package com.isa.analysis.controller;

import com.isa.analysis.neo4jkernel.service.ExpertDetailPageService;
import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by hexu on 2016/12/7.
 */
@Controller
public class EDetailController {

    @Autowired
    private ExpertDetailPageService expertDetailPageService;

    @RequestMapping(value = "/detailOfExpert")
    public String eDetail(){
        return "detailOfExpert";
    }

    @RequestMapping(value = "/detailOfExpert/cooperateOfAuthor")
    public @ResponseBody Map<String, Object> getCooperateRelation(){
        return expertDetailPageService.getRelationshipGraph("詹毅", "电子科技集团36所", 2);
    }
}
