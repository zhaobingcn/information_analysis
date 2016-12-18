package com.isa.analysis.neo4jkernel.repository.impl;

import com.isa.analysis.neo4jkernel.formatservice.MapFormat;
import com.isa.analysis.neo4jkernel.repository.ExpertDetailPageRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
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
            Node row = (Node)result.next().get("p");
            allPapers.add(
                    mapFormat.map("title", row.getProperty("title"),
                            "link", row.getProperty("link"),
                            "quote", row.getProperty("quote"),
                            "date", row.getProperty("date").toString().substring(0, 4)
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

    @Override
    @Transactional
    public List<Map<String, Object>> getcooperateAuthorsByAuthor(String name, String institution) {
        String query = "match (a:Author{name:{name}, institution:{institution}})-[w:work_together]-(b:Author) return b, w.weight as score order " +
                "by score desc limit 8";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("institution", institution);
        Result result = graphDatabaseService.execute(query, params);
        List<Map<String, Object>> authors = new ArrayList<>();
        while(result.hasNext()){
            Map<String, Object> row = result.next();
            Node author = (Node)row.get("b");
            int times = Integer.parseInt(row.get("score").toString());
            authors.add(
                    mapFormat.map("name", author.getProperty("name"),
                                  "institution", author.getProperty("institution"),
                                  "times", times)
            );
        }
        return authors;
    }

    @Override
    @Transactional
    public Map<String, Object> getCooperateInstitutionByAuthor(String name, String institution) {
        String query = "match (a:Author{name:{name}, institution:{institution}})-[w:work_together]-(b:Author)-[r:works_in]->(i:Institution)" +
                " return i.name as iname, count(r) as times order by times desc limit 8";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("institution", institution);
        Result result = graphDatabaseService.execute(query, params);
        Map<String, Object> institutions = new LinkedHashMap<>();
        while(result.hasNext()){
            Map<String, Object> row = result.next();
            institutions.put(row.get("iname").toString(), row.get("times"));
        }
        return institutions;
    }

    @Override
    @Transactional
    public List<Map<String, Object>> getPapersByAuthorWithPages(String name, String institution, int skip, int limit) {
        String query = "match (a:Author{name:{name}, institution:{institution}})-[:publish]-(p:Paper)" +
                "return p order by p.date desc skip {skip} limit {limit}";
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("institution", institution);
        params.put("skip", skip);
        params.put("limit", limit);
        Result result = graphDatabaseService.execute(query, params);
        List<Map<String, Object>> limitPapers = new ArrayList<>();
        while (result.hasNext()){
            Node row = (Node)result.next().get("p");
            limitPapers.add(
                    mapFormat.map("title", row.getProperty("title"),
                            "link", row.getProperty("link"),
                            "quote", row.getProperty("quote")
                    )
            );
        }
        return limitPapers;
    }

}
