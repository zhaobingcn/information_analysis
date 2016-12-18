package test;

import com.isa.analysis.SpringbootSdnEmbeddedApplication;
import com.isa.analysis.neo4jkernel.service.ExpertDetailPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hexu on 2016/12/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringbootSdnEmbeddedApplication.class)
public class ExpertDetailPageServiceTest {

    @Autowired
    private ExpertDetailPageService expertDetailPageService;

    @Test
    public void tsetGetRealtionshipGraph(){

    }

    @Test
    public void testAuthorsAchievement(){
        Map<Integer, ArrayList<Integer>> a = expertDetailPageService.generateAuthorsAchievement("詹毅", "电子科技集团36所");
        for(Map.Entry<Integer, ArrayList<Integer>> s: a.entrySet()){
            System.out.println(s.getKey() +"+"+ s.getValue().get(0) + "+" + s.getValue().get(1));
        }
    }
}
