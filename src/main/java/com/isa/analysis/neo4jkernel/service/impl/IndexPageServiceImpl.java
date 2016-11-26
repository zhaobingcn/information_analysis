package com.isa.analysis.neo4jkernel.service.impl;

import com.isa.analysis.neo4jkernel.formatservice.MapFormat;
import com.isa.analysis.neo4jkernel.repository.IndexPageRepository;
import com.isa.analysis.neo4jkernel.service.IndexPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhzy on 2016/11/26.
 */
@Service
public class IndexPageServiceImpl implements IndexPageService {

    @Autowired
    private IndexPageRepository indexPageRepository;

    @Autowired
    private MapFormat mapFormat;

    @Override
    public Map<String, Object> influentialEntitys(String entityName, int limit) {
        Map<String, Long> hotEntitys = indexPageRepository.tenHotEntitysScopeAll(entityName, limit);
        List<Map<String, Object>> dataGroup = new ArrayList<>();
        for(Map.Entry<String, Long> oneOfEntityData:hotEntitys.entrySet()){
            dataGroup.add(mapFormat.map("name", oneOfEntityData.getKey(), "value", oneOfEntityData.getValue()));
        }
        Map<String, Object> finalInfluentialEntityData = new HashMap<>();
        finalInfluentialEntityData.put("data", dataGroup);
        return finalInfluentialEntityData;
    }

}
