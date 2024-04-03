package university.jala.usersapi;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import university.jala.usersapi.domain.service.security.configuration.WebSecurityConfig;
import university.jala.usersapi.domain.service.security.jwt.JwtAuthenticationFilter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WebSecurityConfigTest {

    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private HttpSecurity http;

    @Mock
    private HttpSecurity httpAfterFilter;

    @Before
    public void setUp() throws Exception {
        when(httpAfterFilter.authorizeHttpRequests(any())).thenReturn(httpAfterFilter);
        when(httpAfterFilter.sessionManagement(any())).thenReturn(httpAfterFilter);
        when(httpAfterFilter.authenticationProvider(any())).thenReturn(httpAfterFilter);
        when(httpAfterFilter.addFilterBefore(any(), any())).thenReturn(httpAfterFilter);
        when(httpAfterFilter.exceptionHandling(any())).thenReturn(httpAfterFilter);
    }

    @Test
    public void givenSecurityConfig_whenCreatingFilterChain_thenAddJwtFilterBeforeUsernamePasswordFilter() throws Exception {
        // Given
        WebSecurityConfig webSecurityConfig = new WebSecurityConfig(jwtAuthenticationFilter, null);

        // When
        SecurityFilterChain filterChain = webSecurityConfig.securityFilterChain(http);

        // Then
        verify(httpAfterFilter).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Test
    public void givenSecurityConfig_whenCreatingFilterChain_WithoutAuthentication() throws Exception {
        // Given
        WebSecurityConfig webSecurityConfig = new WebSecurityConfig(jwtAuthenticationFilter, null);

        // When
        SecurityFilterChain filterChain = webSecurityConfig.securityFilterChain(http);

        // Then
        verify(httpAfterFilter.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry
                -> matches("/usersapi/v1/auth/**")));
    }

    @Test
    public void givenSecurityConfig_thenRequireAuthenticationForOtherRequests() throws Exception {
        // Given
        WebSecurityConfig webSecurityConfig = new WebSecurityConfig(jwtAuthenticationFilter, null);

        // When
        SecurityFilterChain filterChain = webSecurityConfig.securityFilterChain(http);

        // Then
        verify(httpAfterFilter.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry
                -> matches("/usersapi/v1/auth/**")));
    }
}