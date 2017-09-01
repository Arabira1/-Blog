import com.spring.Dao.TagDao;
import com.spring.Entity.TagEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by arabira on 17-8-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Config/application-context.xml","classpath:Config/spring-mvc.xml"})
public class TagTest {
    @Autowired
    private TagDao tagDao;

    @Test
    public void addTag() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                TagEntity tagEntity = new TagEntity();
                tagEntity.setTagName("tag" + i + j);
                tagEntity.setType("type" + i);
                tagDao.addTag(tagEntity);
            }
        }
    }

    @Test
    public void delTest() {
        tagDao.deleteTag("tag00");
    }
}
