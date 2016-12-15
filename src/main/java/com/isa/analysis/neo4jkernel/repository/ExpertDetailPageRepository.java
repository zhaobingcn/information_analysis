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
     * 查询图中作者合作关系的子图,可以定义深度
     * @return 返回子图所有路径
     */
    List<Path> realtionshipPaths(String name, String institution, int depath);

    /**
     * 查询图中作者的论文信息
     * @return
     */


    /**
     * 查询图中作者的合作信息
     * @return
     */

    /**
     * 查询图中作者的研究方向信息
     * @return 关键词详情
     */
    Map<String, Object> getKeywordsByAuthor(String name, String institution);

    /**
     * 查询作者的所有论文及其引用次数
     * @return 论文详情
     */
    List<Map<String, Object>> getPapersByAuthor(String name, String institution);

    int getPapersCountByAuthor(String name, String institution);
}
