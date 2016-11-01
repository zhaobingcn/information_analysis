package com.isa.analysis.neo4jkernel.formatservice;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhzy on 2016/11/1.
 */
@Service
public class MapFormat {

    public Map<String, Object> map(String key1, Object value1, String key2, Object value2){
        Map<String, Object> result = new HashMap<>();
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }

    public Map<String, Object> map(String key1, Object value1, String key2, Object value2, String key3, Object value3){
        Map<String, Object> result = new HashMap<>();
        result.put(key1, value1);
        result.put(key2, value2);
        result.put(key3, value3);
        return result;
    }

    public Map<String, Object> map(String key1, Object value1, String key2, Object value2, String key3, Object value3, String key4, Object value4){
        Map<String, Object> result = new HashMap<>();
        result.put(key1, value1);
        result.put(key2, value2);
        result.put(key3, value3);
        result.put(key4, value4);
        return result;
    }
}
