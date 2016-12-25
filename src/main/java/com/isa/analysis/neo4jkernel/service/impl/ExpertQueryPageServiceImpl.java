package com.isa.analysis.neo4jkernel.service.impl;

import com.isa.analysis.neo4jkernel.formatservice.MapFormat;
import com.isa.analysis.neo4jkernel.repository.ExpertQueryPageRepository;
import com.isa.analysis.neo4jkernel.service.ExpertQueryPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/12/25.
 */
@Service
public class ExpertQueryPageServiceImpl implements ExpertQueryPageService {

    @Autowired
    private ExpertQueryPageRepository authorsQueryPageRepository;
    @Autowired
    private MapFormat mapFormat;

    @Override
    public Map<String, Object> generateQueryAuthorsResult(String name, String institution) {
        Map<String, Object> finalQueryAuthors = new HashMap<>();
        List<Map<String, Object>> queryAuthorsData = authorsQueryPageRepository.getAuthorsQueryResult(name, institution);
        finalQueryAuthors.put("data", queryAuthorsData);
        return finalQueryAuthors;
    }
}
