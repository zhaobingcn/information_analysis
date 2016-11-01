package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Author;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

/**
 * @author zhaobing
 */
@RepositoryRestResource(collectionResourceRel = "author", path = "author")
public interface AuthorRepository extends GraphRepository<Author> {

    Collection<Author> findByNameContaining(@Param("name") String name);

}
