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

import java.util.List;

/**
 * Created by zhzy on 2016/9/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootSdnEmbeddedApplication.class)
public class Neo4jTest {

    @Test
    public void print(){
        System.out.println("hello");
    }
    /**
     * 因为是通过http连接到Neo4j数据库的，所以要预先启动Neo4j
     */
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testFind(){

        List<Author> author = authorRepository.findByName("詹毅");
        for(Author a:author){
            System.out.println(a.getName() + " " + a.getWork().getInstitution().getName());
            for(Paper p: a.getPapers()){
                System.out.println(p.getTitle());
            }
        }

    }


}
