package com.isa.analysis.neo4jkernel.service.impl;

import com.isa.analysis.neo4jkernel.formatservice.MapFormat;
import com.isa.analysis.neo4jkernel.service.GraphService;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2016/11/1.
 */
@Service
@Transactional
public class GraphServiceImpl implements GraphService {

    @Autowired
    private GraphDatabaseService graphDatabaseService;

    @Autowired
    private MapFormat mapFormat;

    @Override
    public Map<String, Object> graph(int limit) {

        StringBuilder queryString = new StringBuilder();
        queryString.append("match (p:Paper)<-[:publish]-(a:Author)-[:work_in]->(i:Institution) ");
        queryString.append("return a.name, i.name, collect(p.title) as cast limit ");
        queryString.append(limit);
        Result result = graphDatabaseService.execute(queryString.toString());
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        List<Map<String, Object>> categories = new ArrayList<>();
        categories.add(mapFormat.map("name", "Author", "keyword", null, "base", "Author"));
        categories.add(mapFormat.map("name", "Institution", "keyword", null, "base", "Institition"));
        categories.add(mapFormat.map("name", "Paper", "keyword", null, "base", "Paper"));

        int i=0;
        while(result.hasNext()){
            Map<String, Object> row = result.next();
            nodes.add(mapFormat.map("name", row.get("a.name"), "value", 1, "category", 0));
            int target1 = i;
            i++;
            nodes.add(mapFormat.map("name", row.get("i.name"), "value", 3, "category", 1));
            int target2 = i;
            rels.add(mapFormat.map("source", target2, "target", target1));
            i++;
            for(Object paperTitle: (Collection<Object>)row.get("cast")){
                Map<String, Object> paper = mapFormat.map("name", row.get("i.name"), "value", 5, "category", 2);
                int target3 = nodes.indexOf(paper);
                if(target3 == -1){
                    nodes.add(paper);
                    target3 = i++;
                }
                rels.add(mapFormat.map("source", target3, "target", target1));
            }
        }
        return mapFormat.map("type", "force", "categories", categories, "nodes", nodes, "links", rels);
    }

}
