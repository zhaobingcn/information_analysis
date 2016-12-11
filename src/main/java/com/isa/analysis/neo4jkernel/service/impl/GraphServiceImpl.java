package com.isa.analysis.neo4jkernel.service.impl;

import com.isa.analysis.neo4jkernel.formatservice.MapFormat;
import com.isa.analysis.neo4jkernel.service.GraphService;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        /**
         *   完整查询语句
             start institution = node(90,91,103,104,105)
             match (a:Author)-[:work_in]->(institution)
             with collect(a) as authors, institution.name as inames
             match (author)-[:publish]->(p:Paper)
             where author in authors
             with collect(p.title) as paper, author, inames
             return inames, collect(author.name) as anames, collect(paper) as papers
         */
        /**
         * 初始加载50个节点做展示
         */
        queryString.append("match (a:Author)-[:work_in]->(institution) ");
        queryString.append("with institution.name as inames,collect(a) as authors limit {limit} ");
        queryString.append("match (author)-[:publish]->(p:Paper) ");
        queryString.append("where author in authors ");
        queryString.append("with collect(p.title) as paper, author, inames ");
        queryString.append("return inames, collect(author.name) as anames, collect(paper) as papers");

        Map<String, Object> params = new HashMap<>();
        params.put("limit", limit);
        Result result = graphDatabaseService.execute(queryString.toString(), params);
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        List<Map<String, Object>> categories = new ArrayList<>();
        categories.add(mapFormat.map("name", "机构", "keyword", null, "base", "Author"));
        categories.add(mapFormat.map("name", "作者", "keyword", null, "base", "Institition"));
        categories.add(mapFormat.map("name", "论文", "keyword", null, "base", "Paper"));

        int i=0;
        while(result.hasNext()){
            Map<String, Object> row = result.next();
            nodes.add(mapFormat.map("name", row.get("inames"), "value", 1, "category", 0));
            int target1 = i;
            i++;
            int anamesIndex = 0;
            /**
             * cypher语句，collect返回的是java Collection类型，不可以强制转换成List，String[]等类型
             */
            for(Object authorName: (Collection<Object>)row.get("anames")){
                nodes.add(mapFormat.map("name", authorName, "value", 3, "category", 1));
                int target2 = i;
                rels.add(mapFormat.map("source", target2, "target", target1));
                i++;

                int paperNameCollectionIndex = 0;
                for(Object paperNameCollection: (Collection<Object>)row.get("papers")){
                    if(anamesIndex == paperNameCollectionIndex){
                        for(Object paperName: (Collection<Object>)paperNameCollection){
                            Map<String, Object> paper = mapFormat.map("name", paperName, "value", 5, "category", 2);
                            int target3 = nodes.indexOf(paper);
                            if(target3 == -1){
                                nodes.add(paper);
                                target3 = i++;
                            }
                            rels.add(mapFormat.map("source", target3, "target", target2));
                        }
                    }
                    paperNameCollectionIndex++;
                }
                anamesIndex++;
            }
        }
        return mapFormat.map("type", "force", "categories", categories, "nodes", nodes, "links", rels);
    }

}
