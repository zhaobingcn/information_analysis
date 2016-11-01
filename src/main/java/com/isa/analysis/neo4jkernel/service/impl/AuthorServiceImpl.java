package com.isa.analysis.neo4jkernel.service.impl;

import com.isa.analysis.neo4jkernel.service.AuthorService;
import com.isa.analysis.sdn.entity.Author;

import java.util.List;

/**
 * Created by hexu on 2016/11/1.
 */
public class AuthorServiceImpl implements AuthorService {
    @Override
    public List<Author> findByName(String name) {
        return null;
    }

    @Override
    public List<Author> findByNameContaining(String partOfName) {
        return null;
    }

    @Override
    public List<Author> findById(Long id) {
        return null;
    }

    @Override
    public List<Author> findByNameAndInstitution(String authorName, String insName) {
        return null;
    }

    @Override
    public List<Author> findByInstitutionContaining(String insName) {
        return null;
    }
}
