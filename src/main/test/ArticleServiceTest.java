import com.spring.Entity.ArticleEntity;
import com.spring.Entity.TagEntity;
import com.spring.Server.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arabira on 17-8-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Config/application-context.xml","classpath:Config/spring-mvc.xml"})
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void addArticle() {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle("title");
        articleEntity.setContent("vvdsafdasfdsafdsafdaf");
        List<TagEntity> list = new ArrayList<TagEntity>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                TagEntity tag = new TagEntity();
                tag.setTagName("tag" + i + j);
                list.add(tag);
            }
        }
        articleEntity.setTags(list);
        articleService.saveTheArticle("user0", articleEntity);
    }

    @Test
    public void findArticle() {
        List<ArticleEntity> list = articleService.findByUserId("user0", 0, 10);
        for (ArticleEntity article: list) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(article.getTitle());
            System.out.println(article.getContent());
            System.out.println(article.getAuthor());
            System.out.println(article.getCreateTime());
            if (article.getTags().size() > 0) {
                for (TagEntity tag: article.getTags()) {
                    System.out.println("            " + tag.getTagName());
                }
            }
            System.out.println("----------------------------------------------------------------------");
        }
    }

    @Test
    public void updateArticle() {
        List<ArticleEntity> list = articleService.findByUserId("user0", 0, 10);
        List<TagEntity> tags = new ArrayList<TagEntity>();
        ArticleEntity article = list.get(0);
        article.setContent("<resultMap id=\"blogResult\" type=\"Blog\">\n" +
                "  <id property=\"id\" column=\"blog_id\" />\n" +
                "  <result property=\"title\" column=\"blog_title\"/>\n" +
                "  <association property=\"author\" column=\"blog_author_id\" javaType=\"Author\" resultMap=\"authorResult\"/>\n" +
                "</resultMap>\n" +
                "\n" +
                "<resultMap id=\"authorResult\" type=\"Author\">\n" +
                "  <id property=\"id\" column=\"author_id\"/>\n" +
                "  <result property=\"username\" column=\"author_username\"/>\n" +
                "  <result property=\"password\" column=\"author_password\"/>\n" +
                "  <result property=\"email\" column=\"author_email\"/>\n" +
                "  <result property=\"bio\" column=\"author_bio\"/>\n" +
                "</resultMap>");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 1; j++) {
                TagEntity tag = new TagEntity();
                tag.setTagName("tag" + i + j);
                tags.add(tag);
            }
        }
        article.setTags(tags);
        articleService.updateTheArticle(article);
    }

    @Test
    public void delArticle() {
        articleService.deleteTheArticle( "4","user0");
    }
}
