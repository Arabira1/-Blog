import com.spring.Dao.KeyDao;
import com.spring.Entity.KeyEntity;
import com.spring.Entity.UserEntity;
import com.spring.Server.UserService;
import com.spring.Util.RSA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by arabira on 17-8-29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Config/application-context.xml","classpath:Config/spring-mvc.xml"})
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private KeyDao keyDao;

    @Test
    public void findKey() throws Exception {

        KeyEntity keyEntity = null/*userService.getNewKey()*/;
        keyEntity = userService.isAlive(keyEntity.getId());
        System.out.println(keyEntity.getType());
    }

    @Test
    public void addKey() throws Exception {
        userService.getNewKey();
    }
    @Test
    public void addUser() throws Exception {
        HttpServletRequest request = new MockHttpServletRequest();
        UserEntity userEntity = new UserEntity();
        userEntity.setLoginName("login1");
        userEntity.setMail("dhsjad@dhsajk.com");
        userEntity.setPassword("123456789");
        userEntity.setPhone("12365254785");
        userEntity.setUserName("user");
        userService.signIn(userEntity,request);
    }

    @Test
    public void updatePassword() {
        HttpServletRequest request = new MockHttpServletRequest();
       // userService.updatePassword("user0", "123456789", "2255663322", request);
    }

    @Test
    public void updateUserInfo() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("user0");
        userEntity.setLoginName("login1");
        userEntity.setMail("dhsjad@dhsajk.com");
        userEntity.setPassword("123456789");
        userEntity.setPhone("12365254785");
        userEntity.setUserName("user78adsa");
        userService.updateUserInfo(userEntity);
    }
}
