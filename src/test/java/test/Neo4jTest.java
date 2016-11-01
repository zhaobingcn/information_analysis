package test;

import com.isa.analysis.SpringbootSdnEmbeddedApplication;
import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.entity.Paper;
import com.isa.analysis.sdn.repository.AuthorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.List;

/**
 * @author zhaobing
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootSdnEmbeddedApplication.class)
public class Neo4jTest {

    @Test
    public void print(){
        System.out.println("hello");
    }
    /**
     * 这里通过embeded的方式连接数据库，不能启动neo4j
     */
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testFind(){

        Collection<Author> author = authorRepository.findByNameContaining("詹毅");
        for(Author a:author){
            System.out.println(a.getName());
        }

    }


}
