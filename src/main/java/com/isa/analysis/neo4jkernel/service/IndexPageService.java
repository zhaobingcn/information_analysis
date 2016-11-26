package com.isa.analysis.neo4jkernel.service;

import java.util.Map;

/**
 * Created by hexu on 2016/11/23.
 */
public interface IndexPageService {

    /**
     * 有影响力的关键词，专家，机构，limit为显示个数
     * @param limit
     * @return
     */
    Map<String, Object> influentialEntitys(String entityName, int limit);

}
