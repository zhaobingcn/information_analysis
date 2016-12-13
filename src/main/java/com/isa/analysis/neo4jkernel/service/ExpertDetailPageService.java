package com.isa.analysis.neo4jkernel.service;

import java.util.Map;

/**
 * Created by hexu on 2016/12/13.
 */
public interface ExpertDetailPageService {

    /**
     * 解析返回的路径数据
     * @return json格式数据
     */
    Map<String, Object> getRelationshipGraph(String name, String institution, int depath);
}
