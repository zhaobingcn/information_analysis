package com.isa.analysis.neo4jkernel.repository.impl;

import com.isa.analysis.neo4jkernel.generic.Labels;
import com.isa.analysis.neo4jkernel.repository.IndexPageRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.helpers.collection.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hexu on 2016/11/23.
 */

@Repository
public class IndexPageRepositoryImpl implements IndexPageRepository {

    @Autowired
    private GraphDatabaseService graphDatabaseService;

    @Override
    @Transactional
    public long totalEntitysScopeAll(String entityName) {

        String cypher = "match (n:" + entityName + ") return count(*) as count";
        Result result = graphDatabaseService.execute(cypher);
        Map<String, Object> countMap = result.next();
        return Long.parseLong(countMap.get("count").toString());
    }

    @Override
    @Transactional
    public Map<String, Long> tenHotEntitysScopeAll(String entityName, int limit) {
        String cypher = "";
        switch (entityName){
            case "Institution":
                cypher = "match (i:Institution)<-[:works_in]-(a:Author)-[pu:publish]->(p:Paper) " +
                        "return i.name as name, sum(pu.weight) as score order by score desc limit {limit}";
                break;
            case "Keyword":
                cypher = "match (k:Keyword)<-[i:involve]-(p:Paper) " +
                        "return k.name as name, count(i) as score order by score desc limit {limit}";
                break;
            case "Author":
                cypher = "match (a:Author)-[pu:publish]->(p:Paper)" +
                        " return a.name as name, sum(pu.weight) as score order by score desc limit {limit}";
                break;
            case "Journal":
                cypher = "match (j:Journal)<-[in:included_in]-(p:Paper) " +
                        "return j.name as name,count(in) as score order by score desc limit {limit}";
        }
        Result result = graphDatabaseService.execute(cypher, MapUtil.map("limit", limit));
        Map<String, Long> entityCount = new LinkedHashMap<>();
        while(result.hasNext()){
            Map<String, Object> row = result.next();
            entityCount.put(row.get("name").toString(), Long.parseLong(row.get("score").toString()));
        }
        return entityCount;
    }

    @Override
    @Transactional
    public Map<String, Long> totalEntitysByNode() {
        ResourceIterator<Node> entityCountNode = graphDatabaseService.findNodes(Labels.EntitysCount);
        Map<String, Long> entityCount = new HashMap<>();
        Node n = entityCountNode.next();
        entityCount.put("AuthorCount", Long.parseLong(n.getProperty("AuthorCount").toString()));
        entityCount.put("InstitutionCount", Long.parseLong(n.getProperty("InstitutionCount").toString()));
        entityCount.put("JournalCount", Long.parseLong(n.getProperty("JournalCount").toString()));
        entityCount.put("KeywordCount", Long.parseLong(n.getProperty("KeywordCount").toString()));
        entityCount.put("PaperCount", Long.parseLong(n.getProperty("PaperCount").toString()));
        return entityCount;
    }
}
