package university.jala.usersapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import university.jala.usersapi.app.UsersApiApplication;

@SpringBootTest(classes = UsersApiApplication.class)
@TestPropertySource(properties = {"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration"})
class UsersApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
