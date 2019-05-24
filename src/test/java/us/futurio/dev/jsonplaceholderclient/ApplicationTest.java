package us.futurio.dev.jsonplaceholderclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.futurio.dev.jsonplaceholderclient.controllers.UserController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private UserController userController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }
}