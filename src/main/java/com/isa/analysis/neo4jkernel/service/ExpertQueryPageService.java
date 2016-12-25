package com.isa.analysis.neo4jkernel.service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/25.
 */
public interface ExpertQueryPageService {

    /**
     * 返回查询的作者列表
     * @param name
     * @param institution
     * @return
     */
    Map<String, Object> generateQueryAuthorsResult(String name, String institution);
}
