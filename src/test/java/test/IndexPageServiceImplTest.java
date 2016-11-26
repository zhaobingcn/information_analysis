package test;

import com.isa.analysis.SpringbootSdnEmbeddedApplication;
import com.isa.analysis.neo4jkernel.service.IndexPageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by zhzy on 2016/11/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootSdnEmbeddedApplication.class)
public class IndexPageServiceImplTest {

    @Autowired
    private IndexPageService indexPageService;

    @Test
    public void testGenerateAuthor(){
        Map<String, Object> a = indexPageService.influentialEntitys("Author", 10);
        System.out.println("asdad");
        for(Map.Entry<String, Object> b:a.entrySet()){
            System.out.println(b.getKey() + "+" + b.getValue());
        }
    }
}
