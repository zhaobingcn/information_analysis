package com.isa.analysis.neo4jkernel.repository;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2016/12/7.
 */


public interface ExpertDetailPageRepository {

    /**
     * 查询图中作者合作关系的子图
     * @return 返回子图所有路径
     */
    List<Path> realtionShipGraph(String name, String institution, int depath);

}
