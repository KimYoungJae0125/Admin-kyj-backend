package kr.kimyoungjae.admin.domain.auth.service;

import kr.kimyoungjae.admin.common.utils.JwtTokenProvider;
import kr.kimyoungjae.admin.domain.auth.model.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static kr.kimyoungjae.admin.common.enums.Roles.USER;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenProvider jwtTokenProvider;
    public TokenDto getToken() {
        return jwtTokenProvider.createToken(Arrays.asList(USER));
    }
}
