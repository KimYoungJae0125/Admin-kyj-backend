package kr.kimyoungjae.admin.domain.auth.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.kimyoungjae.admin.domain.auth.model.TokenDto;
import kr.kimyoungjae.admin.domain.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

@RestController
@RequestMapping("/v1/auths")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @GetMapping
    public TokenDto getToken(HttpServletResponse response) {
        TokenDto newToken = tokenService.getToken();

        Cookie cookie = new Cookie("accesstoken", newToken.token());
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return tokenService.getToken();
    }

}
