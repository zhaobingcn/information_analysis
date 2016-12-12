package test;

import com.isa.analysis.SpringbootSdnEmbeddedApplication;
import com.isa.analysis.neo4jkernel.repository.ExpertDetailPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by zhzy on 2016/12/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootSdnEmbeddedApplication.class)
public class ExpertDetailPageRepositoryImplTest {

    @Autowired
    ExpertDetailPageRepository expertDetailPageRepository;

    @Test
    public void testAuthorCooperate(){
        expertDetailPageRepository.realtionShipGraph("詹毅", "电子科技集团36所", 2);
    }
}
