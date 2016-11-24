package com.isa.analysis.neo4jkernel.repository.impl;

import com.isa.analysis.neo4jkernel.generic.Labels;
import com.isa.analysis.neo4jkernel.repository.IndexPageRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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

        String cypher = "";
        switch (entityName){
            case "Institution":
                cypher = "match (n:Institution) return count(*) as count";
                break;
            case "Keyword":
                cypher = "match (n:Keyword) return count(*) as count";
                break;
            case "Paper":
                cypher = "match (n:Paper) return count(*) as count";
                break;
            case "Journal":
                cypher = "match (n:Journal) return count(*) as count";
                break;
            case "Author":
                cypher = "match (n:Author) return count(*) as count";
                break;
            default:
                break;
        }

        Result result = graphDatabaseService.execute(cypher);
        Map<String, Object> countMap = result.next();
        return Long.parseLong(countMap.get("count").toString());
    }

    @Override
    public Map<String, Long> tenHotEntitysScopeAll(String entityName) {
        String cypher = "";
        switch (entityName){
            case "Institution":
                cypher = "match (i:Institution)<-[:works_in]-(a:Author)-[pu:publish]->(p:Paper)" +
                        " return i, sum(pu.weight)  as point order by point desc limit 10";
                break;
            case "Keyword":
                cypher = "match (k:Keyword)<-[i:involve]-(p:Paper) " +
                        "return k, count(i) as point order by point desc limit 10";
                break;
            case "Author":
                cypher = "";
                break;
        }
        return null;
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
