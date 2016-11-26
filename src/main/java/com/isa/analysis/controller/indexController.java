package com.isa.analysis.controller;

import com.isa.analysis.neo4jkernel.service.IndexPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by hexu on 2016/11/23.
 */
@RestController
public class indexController {

    @Autowired
    private IndexPageService indexPageService;

    @RequestMapping(value = "/pages/wordsCloud")
    public Map<String, Object> wordsCloud(@RequestParam(value = "limit", required = false)int limit){
        String entityName = "Keyword";
        return indexPageService.influentialEntitys(entityName, limit);
    }

    @RequestMapping(value = "/pages/influentialExperts")
    public Map<String, Object> influentialExperts(@RequestParam(value = "limit", required = false)int limit){
        String entityName = "Author";
        return indexPageService.influentialEntitys(entityName, limit);
    }

    @RequestMapping(value = "/pages/influentialInstitutions")
    public Map<String, Object> influentialInstitutions(@RequestParam(value = "limit", required = false)int limit){
        String entityName = "Institution";
        return indexPageService.influentialEntitys(entityName, limit);
    }
}
