package com.isa.analysis.neo4jkernel.repository.impl;

import com.isa.analysis.neo4jkernel.repository.GeneralRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/25.
 */
@Repository
public class GeneralRepositoryImpl implements GeneralRepository {

    @Autowired
    private GraphDatabaseService graphDatabaseService;

    @Override
    @Transactional
    public Long getAuthorsPapersCount(String name, String institution) {
        String query = "match (a:Author{name:{name}}) with a match(b:Author{institution:{institution}})" +
                " where a=b with a match (a)-[:publish]->(p) return count(*) as papersCount";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("institution", institution);
        Result result = graphDatabaseService.execute(query, params);
        Long papersCount = Long.parseLong(result.next().get("papersCount").toString());
        return papersCount;
    }

//    @Override
//    @Transactional
//    public Long getAuthorsQuoteCount(String name, String institution) {
//        String query = "call apoc.index.list yield type, name, config";
//        Result result = graphDatabaseService.execute(query);
//        return null;
//    }

}
