package university.jala.usersapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import university.jala.usersapi.app.UsersApiApplication;

@SpringBootTest(classes = UsersApiApplication.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
class UsersApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
