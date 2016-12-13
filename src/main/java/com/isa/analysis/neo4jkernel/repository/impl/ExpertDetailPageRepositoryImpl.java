package com.isa.analysis.neo4jkernel.repository.impl;

import com.isa.analysis.neo4jkernel.formatservice.MapFormat;
import com.isa.analysis.neo4jkernel.repository.ExpertDetailPageRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Result;
import org.neo4j.helpers.collection.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by hexu on 2016/12/7.
 */
@Repository
public class ExpertDetailPageRepositoryImpl implements ExpertDetailPageRepository{

    @Autowired
    private GraphDatabaseService graphDatabaseService;

    @Override
    @Transactional
    public List<Path> realtionShipGraph(String name, String institution, int depath) {
        String query = "match path = (a:Author{name:{name}, institution:{institution}}) " +
                "-[:work_together*" + depath + "]-(b:Author) return path";
        Map<String, Object> param = new HashMap<>();
        param.put("name", name);
        param.put("institution", institution);
        Result result = graphDatabaseService.execute(query, param);
        List<Path> paths = new ArrayList<>();
        while(result.hasNext()){
            Map<String, Object> row = result.next();
            Path path = (Path) row.get("path");
            paths.add(path);
        }
        return paths;
    }
}
