package com.isa.analysis.neo4jkernel.service.impl;

import com.isa.analysis.neo4jkernel.formatservice.MapFormat;
import com.isa.analysis.neo4jkernel.generic.RelationshipTypes;
import com.isa.analysis.neo4jkernel.repository.ExpertDetailPageRepository;
import com.isa.analysis.neo4jkernel.service.ExpertDetailPageService;
import org.apache.commons.collections.map.HashedMap;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Map<String, Object> getRelationshipGraph(String name, String institution, int depath) {
        List<Path> paths = expertDetailPageRepository.realtionshipPaths(name, institution, depath);
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        Map<Long, Integer> checkNodes = new HashMap<>();
        HashSet<Long> checkRels = new HashSet<>();
        int categoriesCount = depath+1;
        int nodeId = 0;
        /**
         * 遍历每一条作者合作路径
         */
        for(Path path: paths){
            Iterable<Node> pathNodes = path.nodes();
            int pathNodeIndex = 0;
            for(Node node: pathNodes){
                if(checkNodes.containsKey(node.getId())){
                    /**
                     *如果路径中该节点与起点的直接距离更小，那么用更小的距离代替原距离
                     */
                    if(pathNodeIndex < Long.parseLong(nodes.get(checkNodes.get(node.getId())).get("category").toString())){
                        Map<String, Object> author = nodes.get(checkNodes.get(node.getId()));
                        author.replace("category", pathNodeIndex);
                    }
                    pathNodeIndex ++;
                    continue;
                }else{
                    HashMap<String, Object> author = new HashMap<>();
                    author.put("name", node.getProperty("name"));
                    author.put("institution", node.getProperty("institution"));
                    author.put("value", node.getDegree());
                    author.put("category", pathNodeIndex);
                    pathNodeIndex ++;
                    checkNodes.put(node.getId(), nodeId);
                    nodeId ++;
                    nodes.add(author);
                }
            }
            Iterable<Relationship> relationships= path.relationships();
            for(Relationship relationship: relationships){
                if(checkRels.contains(relationship.getId())){
                    continue;
                }else{
                    checkRels.add(relationship.getId());
                    int startNodeId, endNodeId;
                    if(checkNodes.containsKey(relationship.getStartNode().getId())){
                        startNodeId = checkNodes.get(relationship.getStartNode().getId());
                    }else{
                        startNodeId = nodeId ++;
                    }
                    if(checkNodes.containsKey(relationship.getEndNode().getId())){
                        endNodeId = checkNodes.get(relationship.getEndNode().getId());
                    }else{
                        endNodeId = nodeId ++;
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
        for(int categoryIndex=1; categoryIndex<categoriesCount; categoryIndex++){
            categories.add(mapFormat.map("name", categoryIndex+"层合作关系", "keyword", null, "base", "Author"));
        }
        return mapFormat.map("type", "force", "categories", categories, "nodes", nodes, "links", rels);
    }

    @Override
    public Map<String, Object> getKeywordsDetails(String name, String institution) {
        Map<String, Object> keywordsDetail = expertDetailPageRepository.getKeywordsByAuthor(name, institution);
        List<Map<String, Object>> dataGroup = new ArrayList<>();
        for(Map.Entry<String, Object> oneKeyword:keywordsDetail.entrySet()){
            dataGroup.add(mapFormat.map("name", oneKeyword.getKey(), "value", oneKeyword.getValue()));
        }
        Map<String, Object> finalExpertInterestData = new HashMap<>();
        finalExpertInterestData.put("data", dataGroup);
        return finalExpertInterestData;
    }

    @Override
    @Transactional
    public Map<String, Object> generateAuthorAbility(String name, String institution) {
        int resarchWidth, coorpeateAuthors=0, quoteCount=0, papersCount, resarchInfluence;
        double rearchDepath;
        Map<String, Object> keywords = expertDetailPageRepository.getKeywordsByAuthor(name, institution);
        resarchWidth = keywords.size();
        if(resarchWidth > 50){
            resarchWidth = 50;
        }
        int allKeywordsCount = 0;
        for(Map.Entry<String, Object> keyword: keywords.entrySet()){
            allKeywordsCount += Integer.parseInt(keyword.getValue().toString());
        }
        rearchDepath = (double)allKeywordsCount/resarchWidth;
        if(rearchDepath > 2.0){
            rearchDepath = 2.0;
        }
        List<Path> paths = expertDetailPageRepository.realtionshipPaths(name, institution, 1);
        for(Path path: paths){
            Node self = path.startNode();
            Iterable<Relationship> relationships = path.relationships();
            for(Relationship relationship: relationships){
                Node author = relationship.getOtherNode(self);
                coorpeateAuthors += author.getDegree();
            }
        }
        if(coorpeateAuthors > 200){
            coorpeateAuthors = 200;
        }
        List<Map<String, Object>> papers = expertDetailPageRepository.getPapersByAuthor(name, institution);
        papersCount = papers.size();
        if(papersCount > 30){
            papersCount = 30;
        }
        for(Map<String, Object> paper: papers){
            quoteCount += Integer.parseInt(paper.get("quote").toString());
        }
        if(quoteCount > 200){
            quoteCount = 200;
        }
        resarchInfluence = quoteCount + papersCount * 10;
        if(resarchInfluence > 200){
            resarchInfluence = 200;
        }
        Map<String, Object> abilityData = new HashMap<>();
        List<Object> data = new ArrayList<>();
        data.add(papersCount);
        data.add(quoteCount);
        data.add(rearchDepath);
        data.add(resarchWidth);
        data.add(coorpeateAuthors);
        data.add(resarchInfluence);
        abilityData.put("data", data);
        return abilityData;
    }

    @Override
    public List<Map<String, Object>> generateAuthorsPapers(String name, String institution) {
        List<Map<String, Object>> authorPapers = expertDetailPageRepository.getPapersByAuthor(name, institution);
        return authorPapers;
    }

    @Override
    public List<Map<String, Object>> generateAuthorsCoorpeate(String name, String institution) {
        return expertDetailPageRepository.getcooperateAuthorsByAuthor(name, institution);
    }

    @Override
    public Map<String, Object> generateAuthorsCooperateInstitution(String name, String institution) {
        return expertDetailPageRepository.getCooperateInstitutionByAuthor(name, institution);
    }
}
