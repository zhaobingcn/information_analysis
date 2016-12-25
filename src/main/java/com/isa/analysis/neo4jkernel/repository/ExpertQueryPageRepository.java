package com.isa.analysis.neo4jkernel.repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/25.
 */
public interface ExpertQueryPageRepository {

    /**
     * lucene全文检索作者，两个参数都可以为空
     * @param name
     * @param institution
     * @return
     */
    List<Map<String, Object>> getAuthorsQueryResult(String name, String institution);
}
