package com.isa.analysis.neo4jkernel.service.impl;

import com.isa.analysis.neo4jkernel.formatservice.MapFormat;
import com.isa.analysis.neo4jkernel.repository.ExpertDetailPageRepository;
import com.isa.analysis.neo4jkernel.service.ExpertDetailPageService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hexu on 2016/12/13.
 */
@Service
public class ExpertDetailPageServiceImpl implements ExpertDetailPageService {

    @Autowired
    private ExpertDetailPageRepository expertDetailPageRepository;

    @Autowired
    private MapFormat mapFormat;

    @Override
    public Map<String, Object> getRelationshipGraph(String name, String institution, int depath) {
        List<Path> paths = expertDetailPageRepository.realtionShipGraph(name, institution, depath);
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        Map<Long, Integer> checkNodes = new HashMap<>();
        Map<Long, Integer> checkRels = new HashMap<>();
        int nodeId = 0;
        for(Path path: paths){
            Iterable<Node> pathNodes = path.nodes();
            for(Node node: pathNodes){
                if(checkNodes.containsKey(node.getId())){
                    continue;
                }else{
                    nodeId ++;
                    HashMap<String, Object> author = new HashMap<>();
                    author.put("name", node.getProperty("name"));
                    author.put("institution", node.getProperty("institution"));
                    checkNodes.put(node.getId(), nodeId);
                    nodes.add(author);
                }
            }
            Iterable<Relationship> relationships= path.relationships();
            for(Relationship relationship: relationships){
                if(checkRels.containsKey(relationship.getId())){
                    continue;
                }else{
                    int startNodeId, endNodeId;
                    if(checkNodes.containsKey(relationship.getStartNode().getId())){
                        startNodeId = checkNodes.get(relationship.getStartNode().getId());
                    }else{
                        startNodeId = ++nodeId;
                    }
                    if(checkNodes.containsKey(relationship.getEndNode().getId())){
                        endNodeId = checkNodes.get(relationship.getEndNode().getId());
                    }else{
                        endNodeId = ++nodeId;
                    }
                    HashMap<String, Object> rel = new HashMap<>();
                    rel.put("source", startNodeId);
                    rel.put("target", endNodeId);
                    rel.put("value", relationship.getProperty("weight"));
                    rels.add(rel);
                }
            }
        }
        List<Map<String, Object>> categories = new ArrayList<>();
        categories.add(mapFormat.map("name", "专家", "keyword", null, "base", "Author"));
        return mapFormat.map("type", "force", "categories", categories, "nodes", nodes, "links", rels);
    }
}
