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
     * 查询图中作者的研究方向信息
     * @return 关键词详情
     */
    Map<String, Object> getKeywordsByAuthor(String name, String institution);

    /**
     * 查询作者的所有论文及其引用次数
     * @return 论文详情
     */
    List<Map<String, Object>> getPapersByAuthor(String name, String institution);

    /**
     * 查询一个作者的论文数量
     * @param name
     * @param institution
     * @return
     */
    int getPapersCountByAuthor(String name, String institution);

    /**
     * 查询和一个作者紧密合作的作者，按照合作次数排序
     */
    List<Map<String, Object>> getcooperateAuthorsByAuthor(String name, String institution);

    /**
     * 查询和一个作者紧密合作的作者的所属机构，按合作次数排序
     */
    Map<String, Object> getCooperateInstitutionByAuthor(String name, String institution);

    /**
     * 查询一个作者发的论文，有分页
     *
     */
    List<Map<String, Object>> getPapersByAuthorWithPages(String name, String institution, int skip, int limit);
}
