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

    @Autowired
    private MapFormat mapFormat;

    @Override
    @Transactional
    public List<Path> realtionshipPaths(String name, String institution, int depath) {
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

    @Override
    @Transactional
    public Map<String, Object> getKeywordsByAuthor(String name, String institution) {
        String query = "match (a:Author{name:{name}, institution:{institution}})" +
                "-[:publish]->(p:Paper)-[i:involve]->(k:Keyword) return k.name as kname, count(i) as times";
        Map<String, Object> params =  new HashMap<>();
        params.put("name", name);
        params.put("institution", institution);
        Result result = graphDatabaseService.execute(query, params);
        Map<String, Object> keywords = new HashMap<>();
        while (result.hasNext()){
            Map<String, Object> row = result.next();
            keywords.put(row.get("kname").toString(), row.get("times"));
        }
        return keywords;
    }

    @Override
    @Transactional
    public List<Map<String, Object>> getPapersByAuthor(String name, String institution) {
        String query = "match (a:Author{name:{name}, institution:{institution}})" +
                "-[:publish]->(p:Paper) return p";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("institution", institution);
        Result result = graphDatabaseService.execute(query, params);
        List<Map<String, Object>> allPapers = new ArrayList<>();
        while (result.hasNext()){
            Map<String, Object> row = new HashMap<>();
            allPapers.add(
                    mapFormat.map("title", row.get("title"),
                            "link", row.get("link"),
                            "quote", row.get("quote"),
                            "date", row.get("date").toString().substring(0, 3)
                            )
            );
        }
        return allPapers;
    }

    @Override
    @Transactional
    public int getPapersCountByAuthor(String name, String institution){

        String query = "match (a:Author{name:{name}, institution:{institution}})" +
                "-[:publish]->(p:Paper) return count(p) as pcount";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("institution", institution);
        Result result = graphDatabaseService.execute(query, params);
        Map<String, Object> pcountCollect = result.next();
        int pcount = Integer.parseInt(pcountCollect.get("pcount").toString());
        return  pcount;
    }
}
