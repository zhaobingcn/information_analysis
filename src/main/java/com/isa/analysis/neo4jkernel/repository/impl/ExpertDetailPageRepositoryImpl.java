package com.isa.analysis.neo4jkernel.repository.impl;

import com.isa.analysis.neo4jkernel.repository.ExpertDetailPageRepository;
import org.apache.commons.collections.map.HashedMap;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2016/12/7.
 */
@Repository
public class ExpertDetailPageRepositoryImpl implements ExpertDetailPageRepository{

    @Autowired
    private GraphDatabaseService graphDatabaseService;

    @Override
    @Transactional
    public Map<String, Object> realtionShipGraph(String name, String institution, int depath) {
        String query = "match p= (a:Author{name:{name}, institution:{institution}})-[:work_together*..{depath}]-(b:Author) return p"
                ;  //TODO 12.11
        Map<String, Object> parama = new HashedMap();
        parama.put("name", name);
        parama.put("institution", institution);
        parama.put("depath", depath);
        Result result =  graphDatabaseService.execute(query, parama);
        while(result.hasNext()){
            Map<String, Object> row = result.next();
            List<Map<String, Object>> rowList = (List<Map<String, Object>>)row.get("p");

        }
        return null;
    }
}
