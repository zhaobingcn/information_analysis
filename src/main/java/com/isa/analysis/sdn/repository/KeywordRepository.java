package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Keyword;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author zhaobing
 */
public interface KeywordRepository extends GraphRepository<Keyword> {
}
