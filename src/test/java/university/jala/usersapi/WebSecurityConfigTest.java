package university.jala.usersapi;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import university.jala.usersapi.domain.service.security.configuration.WebSecurityConfig;
import university.jala.usersapi.domain.service.security.jwt.JwtAuthenticationFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WebSecurityConfigTest {

    @InjectMocks
    private WebSecurityConfig webSecurityConfig;

    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @Mock
    private HttpSecurity httpSecurity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSecurityFilterChain() throws Exception {
        SecurityFilterChain securityFilterChain = mock(SecurityFilterChain.class);
        when(httpSecurity.build()).thenReturn((DefaultSecurityFilterChain) securityFilterChain);

        webSecurityConfig.securityFilterChain(httpSecurity);

        verify(httpSecurity, times(1)).csrf(any());
        verify(httpSecurity, times(1)).authorizeHttpRequests(any());
        verify(httpSecurity, times(1)).sessionManagement(any());
        verify(httpSecurity, times(1)).authenticationProvider(authenticationProvider);
        verify(httpSecurity, times(1)).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        verify(httpSecurity, times(1)).exceptionHandling(any());
        verify(httpSecurity, times(1)).build();
    }
}
