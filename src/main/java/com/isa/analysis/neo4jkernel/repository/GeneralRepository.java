package com.isa.analysis.neo4jkernel.repository;

/**
 * Created by hexu on 2016/12/16.
 */
public interface GeneralRepository {

    /**
     * 获取作者发表的论文数
     * @param name
     * @param institution
     * @return
     */
    Long getAuthorsPapersCount(String name, String institution);

    /**
     * 获取作者发表的论文引用次数
     * @param name
     * @param institution
     * @return
     */
//    Long getAuthorsQuoteCount(String name, String institution);

}
