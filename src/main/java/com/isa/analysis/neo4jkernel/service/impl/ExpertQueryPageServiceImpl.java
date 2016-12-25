package com.isa.analysis.neo4jkernel.service.impl;

import com.isa.analysis.neo4jkernel.repository.ExpertQueryPageRepository;
import com.isa.analysis.neo4jkernel.service.ExpertQueryPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/25.
 */
@Service
public class ExpertQueryPageServiceImpl implements ExpertQueryPageService {

    @Autowired
    private ExpertQueryPageRepository authorsQueryPageRepository;

    @Override
    public List<Map<String, Object>> generateQueryAuthorsResult(String name, String institution) {
        return authorsQueryPageRepository.getAuthorsQueryResult(name, institution);
    }
}
