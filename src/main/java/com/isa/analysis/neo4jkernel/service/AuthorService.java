package com.isa.analysis.neo4jkernel.service;

import com.isa.analysis.sdn.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hexu on 2016/11/1.
 */
@Service
public interface AuthorService {
    /*
    根据作者名字查询
     */
    List<Author> findByName(String name);
    /*
    根据作者名字模糊查询
     */
    List<Author> findByNameContaining(String partOfName);
    /*
    根据id查询
     */
    List<Author> findById(Long id);
    /*
    根据作者名字和机构名查询
     */
    List<Author> findByNameAndInstitution(String authorName, String insName);
    /*
    根据机构名字模糊查询
     */
    List<Author> findByInstitutionContaining(String insName);
}
