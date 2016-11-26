package test;

import com.isa.analysis.SpringbootSdnEmbeddedApplication;
import com.isa.analysis.neo4jkernel.repository.IndexPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by zhzy on 2016/11/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootSdnEmbeddedApplication.class)
public class IndexPageRepositoryImplTest {

    @Autowired
    private IndexPageRepository indexPageRepository;

    @Test
    public void testTotalEntitys(){
        System.out.println("1233");
        System.out.println("count" + indexPageRepository.totalEntitysScopeAll("Paper"));
//        assertEquals(9325l, indexPageRepository.totalEntitysScopeAll("Institution"));
    }

    @Test
    public void testTotalEntitysByNode(){
        Map<String, Long> a = indexPageRepository.totalEntitysByNode();
        System.out.println("123");
        System.out.println(a.get("AuthorCount") + " " + a.get("InstitutionCount") + " "
               + a.get("PaperCount") + " " + a.get("KeywordCount"));
    }

    @Test
    public void testTenHotEntitysScopeAll(){
        Map<String, Long> a = indexPageRepository.tenHotEntitysScopeAll("Journal", 10);
        for(Map.Entry<String, Long> map: a.entrySet()){
            System.out.println(map.getKey() + "+" + map.getValue());
        }
    }
}
