package com.isa.analysis.neo4jkernel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2016/12/13.
 */
public interface ExpertDetailPageService {

    /**
     * 解析返回的路径数据
     *
     * @return json格式数据
     */
    Map<String, Object> getRelationshipGraph(String name, String institution, int depath);

    /**
     * 包装成echarts可以用的json格式数据
     *
     * @return json格式数据
     */
    Map<String, Object> getKeywordsDetails(String name, String institution);

    /**
     * 生成专家能力雷达图
     *
     * @return 五个能力指标
     */
    Map<String, Object> generateAuthorAbility(String name, String institution);

    /**
     * 作者发过的论文详情,带有分页功能
     */
    List<Map<String, Object>> generateAuthorsPapersPages(String name, String institution, int skip, int limit);

    /**
     * 作者发过的论文，不带分页功能
     * @param name
     * @param institution
     * @return
     */
    List<Map<String, Object>> generateAuthorsPapers(String name, String institution);

    /**
     * 作者合作过的专家列表
     */
    List<Map<String, Object>> generateAuthorsCoorpeate(String name, String institution);

    /**
     * 作者合作过的机构列表
     */
    Map<String, Object> generateAuthorsCooperateInstitution(String name, String institution);

    /**
     * 作者的论文数量
     */
    int generateAuthorsPapersCount(String name, String institution);

    /**
     * 作者发表的论文，按照年份排序，2006-2016
     */
    Map<Integer, ArrayList<Integer>> generateAuthorsAchievement(String name, String institution);
}