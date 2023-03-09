package kr.kimyoungjae.admin.common.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kimyoungjae.admin.common.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.getToken(request.getCookies());
        if(jwtTokenProvider.validate(token)) {
            this.setAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String token) {
        log.debug("권한 : {}", jwtTokenProvider.getAuthentication(token));
        SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(token));
    }

}
