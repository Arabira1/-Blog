import com.spring.Dao.UserDao;
import com.spring.Util.DataSource.DynamicDataSorce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by arabira on 17-9-11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:Config/application-context.xml", "classpath*:Config/spring-mvc.xml"})
public class DataSourceTest {

    @Autowired
    private DynamicDataSorce dynamicDataSorce;
    @Autowired
    private UserDao userDao;
    @Test
    public void test() {
        String sqlCheck = "select * from user;";
        List<Map> list = userDao.test(sqlCheck);
        System.out.println(String.valueOf(list.size()));
        dynamicDataSorce.createDataSource("dataSource3",
                "jdbc:mysql://127.0.0.1:3306/blog2?allowMultiQueries=true&amp;useUnicode=true", "root", "03017");
        dynamicDataSorce.setDataSource("dataSource3");
        list = userDao.test(sqlCheck);
        System.out.println(String.valueOf(list.get(0)));
        dynamicDataSorce.deleteTheDataSource("dataSource3");
        dynamicDataSorce.setDataSource("");
        list = userDao.test(sqlCheck);
        System.out.println(String.valueOf(list.get(0)));
    }
}
