package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Institution;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * @author zhaobing
 */
public interface InstitutionRepository extends GraphRepository<Institution> {
}
