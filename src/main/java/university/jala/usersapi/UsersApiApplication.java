package university.jala.usersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main application class for Users API.
 */
@SpringBootApplication
public class UsersApiApplication {

    protected UsersApiApplication() {

    }

    /**
     * The main method which runs the application.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(UsersApiApplication.class, args);
    }
}
