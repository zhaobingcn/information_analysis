package com.isa.analysis.neo4jkernel.service.impl;

import com.isa.analysis.neo4jkernel.generic.RelationshipTypes;
import com.isa.analysis.neo4jkernel.service.AuthorService;
import com.isa.analysis.sdn.entity.Author;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.ogm.json.JSONArray;
import org.neo4j.ogm.json.JSONException;
import org.neo4j.ogm.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hexu on 2016/11/1.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private GraphDatabaseService graphDatabaseService;

    @Override
    @Transactional
    public String findByName(String name) {
        IndexManager indexManager = graphDatabaseService.index();
        Index<Node> authorIndex = indexManager.forNodes("author");
        IndexHits<Node> indexHits = authorIndex.get("name", name);
        JSONArray authorsArray = new JSONArray();

        for(Node author: indexHits){
            JSONObject authorObject = new JSONObject();
            try {
                authorObject.put("authorName", author.getProperty("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            author.getRelationships(Direction.OUTGOING, RelationshipTypes.work_in).forEach(
                    relationship -> {
                        Node insNode = relationship.getEndNode();
                        try {
                            authorObject.put("insName", insNode.getProperty("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
            );
            JSONArray paperArray = new JSONArray();
            author.getRelationships(Direction.OUTGOING, RelationshipTypes.publish).forEach(
                    relationship -> {
                        Node paperNode = relationship.getEndNode();
                        paperArray.put(paperNode.getProperty("title"));
                    }
            );
            try {
                authorObject.put("papers", paperArray);
                authorsArray.put(authorObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return authorsArray.toString();
    }

    @Override
    public List<Author> findByNameContaining(String partOfName) {
        return null;
    }

    @Override
    public List<Author> findById(Long id) {
        return null;
    }

    @Override
    public List<Author> findByNameAndInstitution(String authorName, String insName) {
        return null;
    }

    @Override
    public List<Author> findByInstitutionContaining(String insName) {
        return null;
    }
}
