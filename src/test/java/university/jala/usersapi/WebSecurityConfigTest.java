package university.jala.usersapi;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import university.jala.usersapi.domain.service.security.configuration.WebSecurityConfig;
import university.jala.usersapi.domain.service.security.jwt.JwtAuthenticationFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Before
    public void setUp() {
        webSecurityConfig = new WebSecurityConfig(jwtAuthenticationFilter,
                (AuthenticationProvider) authenticationEntryPoint);
    }

    @Test
    public void securityFilterChain_ConfiguresSecurityCorrectly() throws Exception {
        // Given
        when(httpAfterFilter.authorizeHttpRequests(any())).thenReturn(httpAfterFilter);
        when(httpAfterFilter.sessionManagement(any())).thenReturn(httpAfterFilter);
        when(httpAfterFilter.authenticationProvider(any())).thenReturn(httpAfterFilter);
        when(httpAfterFilter.addFilterBefore(any(), any())).thenReturn(httpAfterFilter);
        when(httpAfterFilter.exceptionHandling(any())).thenReturn(httpAfterFilter);

        // When
        SecurityFilterChain filterChain = webSecurityConfig.securityFilterChain(http);

        // Then
        verify(httpAfterFilter).sessionManagement(any());
        verify(httpAfterFilter).authenticationProvider(any());
        verify(httpAfterFilter).addFilterBefore(jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);
        verify(httpAfterFilter).exceptionHandling(any());
        verify(httpAfterFilter).build();
    }
}