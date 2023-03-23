package kr.kimyoungjae.admin.common.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kimyoungjae.admin.common.utils.JsonUtils;
import kr.kimyoungjae.admin.common.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .sessionManagement(sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .cors(cors -> cors.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize ->
                    authorize
                            .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PATCH).hasRole("ADMIN")
                            .requestMatchers("/h2-console/**", "/v1/auths/**", "/health", "/v1/posts/**").permitAll()
                            .anyRequest().authenticated()
                )
                .exceptionHandling(handler ->
                        handler.accessDeniedHandler(accessDeniedHandler())
                                .authenticationEntryPoint(authenticationEntryPoint()))
                .build();
    }

    private void createErrorMessage(HttpServletRequest request, HttpServletResponse response, String exceptionMessage) throws IOException {
        String token = jwtTokenProvider.getToken(request.getCookies());
        String roles = "NO_ROLE";
        if(jwtTokenProvider.validate(token)) {
            roles = jwtTokenProvider.getAuthentication(token).getAuthorities().stream().map(String::valueOf).collect(Collectors.joining(", "));
        }
        response.setStatus(HttpStatus.FORBIDDEN.value());
        String json = JsonUtils.objectToJson(new ErrorMessage(roles, exceptionMessage));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.flush();
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> createErrorMessage(request, response, authException.getMessage());
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> createErrorMessage(request, response, accessDeniedException.getMessage());
    }

    record ErrorMessage(String roles, String exceptionMessage) {
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
