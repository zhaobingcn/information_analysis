package test;

import com.isa.analysis.SpringbootSdnEmbeddedApplication;
import com.isa.analysis.neo4jkernel.repository.ExpertDetailPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional
    public void testAuthorCooperate(){
        List<Path> paths  = expertDetailPageRepository.realtionshipPaths("詹毅", "电子科技集团36所", 3);
        for(Path path: paths){
            System.out.println(path);
        }
    }
}
