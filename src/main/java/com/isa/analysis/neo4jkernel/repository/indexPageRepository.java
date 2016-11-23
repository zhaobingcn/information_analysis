package com.isa.analysis.neo4jkernel.repository;

/**
 * Created by hexu on 2016/11/23.
 */
public interface indexPageRepository {

    /**
     * 查询机构总数
     */
    long totalInstitutionsByCount();
    /**
     * 查询论文总数
     */
    long totalPapersByCount();
    /**
     * 查询关键词总数
     */
    long totalKeywordsByCount();
    /**
     * 查询专家总数
     */
    long totalAuthorByCount();

    /**
     *  查询
     */

}
