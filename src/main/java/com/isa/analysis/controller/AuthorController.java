package com.isa.analysis.controller;

import com.isa.analysis.neo4jkernel.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hexu on 2016/11/1.
 */
@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/newauthors")
    public String newauthors(@RequestParam(value = "name", required = false)String name){
        return authorService.findByName(name);
    }
}
