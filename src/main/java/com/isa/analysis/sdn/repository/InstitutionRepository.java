package com.isa.analysis.sdn.repository;

import com.isa.analysis.sdn.entity.Institution;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by hexu on 2016/10/19.
 */
public interface InstitutionRepository extends GraphRepository<Institution> {
}
