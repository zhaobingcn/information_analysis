package com.isa.analysis.neo4jkernel.repository.impl;

import com.isa.analysis.neo4jkernel.repository.ExpertDetailPageRepository;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public Map<String, Object> realtionShipGraph() {


        return null;
    }
}
