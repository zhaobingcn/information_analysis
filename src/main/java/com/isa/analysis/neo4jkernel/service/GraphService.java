package com.isa.analysis.neo4jkernel.service;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by hexu on 2016/11/1.
 */
public interface GraphService {

    Map<String, Object> graph(int limit);
 }
