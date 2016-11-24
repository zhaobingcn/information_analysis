package com.isa.analysis.neo4jkernel.repository;

import java.util.Map;

/**
 * Created by hexu on 2016/11/23.
 */
public interface IndexPageRepository {


    /**
     * 查询全局范围内的实体数量
     * @param entityName
     * @return
     */
    long totalEntitysScopeAll(String entityName);

    /**
     * 根据信息存储节点查询
     * @return
     */
    Map<String, Long> totalEntitysByNode();
}
