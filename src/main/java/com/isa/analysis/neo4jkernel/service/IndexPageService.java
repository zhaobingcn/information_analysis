package com.isa.analysis.neo4jkernel.service;

import java.util.Map;

/**
 * Created by hexu on 2016/11/23.
 */
public interface IndexPageService {

    /**
     * 生成词云的方法，limit为显示关键词个数，暂定100
     * @param limit
     * @return
     */
    Map<String, Object> cloudKeyword(int limit);
}
