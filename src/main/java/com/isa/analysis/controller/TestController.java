package com.isa.analysis.controller;

import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.util.Collection;
import java.util.List;

/**
 * @author zhaobing
 */
@RestController
public class TestController {                       // TODO: 2016/10/20 need to be completed

    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(value = "/author")
    private Collection<Author> getAuthor(@RequestParam(value = "name", required = false) String name) throws Exception{
            String nnn = URLDecoder.decode("%E8%A9%B9", "UTF-8");
            System.out.println(nnn);
            return authorRepository.findByNameContaining(nnn);
    }
}
