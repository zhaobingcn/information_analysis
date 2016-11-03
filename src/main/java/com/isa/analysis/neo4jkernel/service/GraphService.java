package com.isa.analysis.neo4jkernel.service;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by hexu on 2016/11/1.
 */
public interface GraphService {

    /**
     * 加载页面初始的图像，用了match语句查询，返回是一个Map,可以转化成echarts的json数据。
     * @param limit
     * @return Map
     */
    Map<String, Object> graph(int limit);
 }
