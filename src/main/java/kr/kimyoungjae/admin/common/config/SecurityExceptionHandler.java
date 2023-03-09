package kr.kimyoungjae.admin.common.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kimyoungjae.admin.common.utils.JsonUtils;
import kr.kimyoungjae.admin.common.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SecurityExceptionHandler implements AccessDeniedHandler, AuthenticationEntryPoint {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        this.exceptionMessage(request, response);

    }


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        this.exceptionMessage(request, response);
    }

    private void exceptionMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "권한에 문제가 있습니다.");
        response.setContentType("application/json; charset=utf-8");

        Map<String, String> authentication = new HashMap<>();
        String token = jwtTokenProvider.getToken(request.getCookies());
        String authenticate = "NO_ROLE";
        if(jwtTokenProvider.validate(token)) {
            authenticate = jwtTokenProvider.getAuthentication(token).getAuthorities().stream().map(String::valueOf).collect(Collectors.joining(", "));
        }

        authentication.put("authenticate", authenticate);

        String errorBody = JsonUtils.objectToJson(authentication);

        PrintWriter writer = response.getWriter();
        writer.println(errorBody);
    }

}
