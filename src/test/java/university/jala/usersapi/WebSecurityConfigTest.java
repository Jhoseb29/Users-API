package university.jala.usersapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import university.jala.usersapi.core.application.security.configuration.WebSecurityConfig;
import university.jala.usersapi.core.application.security.jwt.JwtAuthenticationFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WebSecurityConfigTest {

    @Mock
    private HttpSecurity http;

    @Mock
    private HttpSecurity httpAfterFilter;

    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private AuthenticationEntryPoint authenticationEntryPoint;

    private WebSecurityConfig webSecurityConfig;

    @BeforeEach
    public void setUp() {
        webSecurityConfig = new WebSecurityConfig(jwtAuthenticationFilter,
                (AuthenticationProvider) authenticationEntryPoint);
    }
}