package com.isa.analysis.neo4jkernel.repository.impl;

import com.isa.analysis.neo4jkernel.generic.RelationshipTypes;
import com.isa.analysis.neo4jkernel.repository.AuthorsQueryPageRepository;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.index.lucene.QueryContext;
import org.neo4j.kernel.impl.coreapi.IndexManagerImpl;
import org.neo4j.kernel.impl.coreapi.IndexProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by zhzy on 2016/12/25.
 */
@Repository
public class AuthorsQueryPageRepositoryImpl implements AuthorsQueryPageRepository {

    @Autowired
    private GraphDatabaseService graphDatabaseService;

    @Override
    @Transactional
    public List<Map<String, Object>> getAuthorsQueryResult(String name, String institution) {
        String queryContext = "";
        if(name != null && institution != null){
            queryContext = "name:(" + name + ") AND " + "institution:(" + institution + ")";
        }else if(name == null){
            queryContext = "institution:(" + institution + ")";
        }else if(institution == null){
            queryContext = "name:(" + name + ")";
        }
        IndexManager indexManager = graphDatabaseService.index();
        Index<Node> nodeFulltextIndex = indexManager.forNodes("author", MapUtil.stringMap(
                IndexManager.PROVIDER, "lucene",
                "analyzer", "org.wltea.analyzer.lucene.IKAnalyzer",
                "type", "fulltext"));
        IndexHits<Node> nodes = nodeFulltextIndex.query(new QueryContext(queryContext).sortByScore().top(30));
        List<Map<String, Object>> queryAuthorsResult = new ArrayList<>();
        for(Node node: nodes){
            Map<String, Object> author = new HashMap<>();
            int papersCount = node.getDegree(RelationshipTypes.publish, Direction.OUTGOING);
            List<Integer> authorsPapersQuote = new ArrayList<>();
            node.getRelationships(RelationshipTypes.publish, Direction.OUTGOING).forEach(relationship -> {
                int quote = Integer.parseInt(relationship.getEndNode().getProperty("quote").toString());
                authorsPapersQuote.add(quote);
            });
            int papersQuote =  authorsPapersQuote.stream().map((quote) -> quote).reduce((sum, quote)-> sum + quote).get();
            author.put("name", node.getProperty("name"));
            author.put("institution", node.getProperty("institution"));
            author.put("papersQuote", papersQuote);
            author.put("papersCount", papersCount);
            queryAuthorsResult.add(author);
        }
        return queryAuthorsResult;
    }
}
