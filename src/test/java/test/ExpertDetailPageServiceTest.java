package test;

import com.isa.analysis.SpringbootSdnEmbeddedApplication;
import com.isa.analysis.neo4jkernel.service.ExpertDetailPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
