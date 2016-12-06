package com.isa.analysis.controller;

import com.isa.analysis.neo4jkernel.service.IndexPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hexu on 2016/11/23.
 */
@Controller
public class IndexController {

    @Autowired
    private IndexPageService indexPageService;

    @RequestMapping(value = "/pages/wordsCloud")
    public @ResponseBody Map<String, Object> wordsCloud(@RequestParam(value = "limit", required = false)int limit){
        String entityName = "Keyword";
        return indexPageService.influentialEntitys(entityName, limit);
    }

    @RequestMapping(value = "/pages/influentialExperts")
    public @ResponseBody Map<String, Object> influentialExperts(@RequestParam(value = "limit", required = false)int limit){
        String entityName = "Author";
        return indexPageService.influentialEntitys(entityName, limit);
    }

    @RequestMapping(value = "/pages/influentialInstitutions")
    public @ResponseBody Map<String, Object> influentialInstitutions(@RequestParam(value = "limit", required = false)int limit){
        String entityName = "Institution";
        return indexPageService.influentialEntitys(entityName, limit);
    }

    @RequestMapping(value = "/pages/influentialJournals")
    public @ResponseBody Map<String, Object> influentialJournals(@RequestParam(value = "limit", required = false)int limit){
        String entityName = "Journal";
        return indexPageService.influentialEntitys(entityName, limit);
    }

    @RequestMapping("/index")
    public String index(HttpSession session){
        session.setAttribute("name", "zhao");
//        HttpSession session = request.getSession();
//        session.setAttribute("name", "zhaobing");
        return "/index";
    }
}
