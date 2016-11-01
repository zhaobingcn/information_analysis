package com.isa.analysis.neo4jkernel.service.impl;

import com.isa.analysis.neo4jkernel.service.GraphService;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by hexu on 2016/11/1.
 */
@Service
@Transactional
public class GraphServiceImpl implements GraphService {

    @Autowired
    private GraphDatabaseService graphDatabaseService;

    @Override
    public Map<String, Object> graph(int limit) {

        StringBuilder queryString = new StringBuilder();
        queryString.append("match (p:Paper)<-[:publish]-(a:author)-[:work_in]->(i:Institution) ");
        queryString.append("return a.name, i.name, collect(p.name) as cast limit ");
        queryString.append(limit);
        Result result = graphDatabaseService.execute(queryString.toString());
        System.out.println("qqqqqqqqqq");
        while(result.hasNext()){
            System.out.println("jjjjjj");
            System.out.println(result.next().get("a.name"));
        }
        return null;
    }
}
