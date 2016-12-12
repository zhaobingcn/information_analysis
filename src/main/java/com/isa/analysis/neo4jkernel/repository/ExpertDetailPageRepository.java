package com.isa.analysis.neo4jkernel.repository;

import org.apache.commons.collections.map.HashedMap;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by hexu on 2016/12/7.
 */


public interface ExpertDetailPageRepository {

    /**
     * 查询图中作者合作关系的子图
     * @return
     */
    Map<String, Object> realtionShipGraph(String name, String institution, int depath);
}
