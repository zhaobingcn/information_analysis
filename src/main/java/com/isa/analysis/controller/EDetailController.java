package com.isa.analysis.controller;

import com.isa.analysis.neo4jkernel.service.ExpertDetailPageService;
import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String eDetail(Model model) {

        return "detailOfExpert";
    }

    @RequestMapping(value = "/detailOfExpert/cooperateOfAuthor")
    public @ResponseBody Map<String, Object> cooperateRelation(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "institution", required = false)String institution,
            @RequestParam(value = "depath", required = false)int depath
    ){
        return expertDetailPageService.getRelationshipGraph(name, institution, depath);
    }

    @RequestMapping(value = "/detailOfExpert/InterestOfExpert")
    public  @ResponseBody Map<String, Object> interestOfExpert(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "institution", required = false)String institution
    ){
        return expertDetailPageService.getKeywordsDetails(name, institution);
    }

    @RequestMapping(value = "/detailOfExpert/abilityOfExpert")
    public @ResponseBody Map<String, Object> abilityOfExpert(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "institution", required = false)String institution
    ){
        return expertDetailPageService.generateAuthorAbility(name, institution);
    }
}
