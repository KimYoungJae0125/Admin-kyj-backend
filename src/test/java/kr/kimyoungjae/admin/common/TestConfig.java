package kr.kimyoungjae.admin.common;

import jakarta.servlet.http.Cookie;
import kr.kimyoungjae.admin.common.enums.Roles;
import kr.kimyoungjae.admin.common.utils.JwtTokenProvider;
import kr.kimyoungjae.admin.domain.auth.model.TokenDto;
import kr.kimyoungjae.admin.domain.introduces.model.dto.request.IntroducesRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;

import java.util.Arrays;

import static kr.kimyoungjae.admin.common.utils.JsonUtils.objectToJson;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestConfig extends ContainerConfig {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected JwtTokenProvider jwtTokenProvider;

    protected Cookie userTokenCookie() {
        TokenDto tokenDto = jwtTokenProvider.createToken(Arrays.asList(Roles.USER));
        return new Cookie("accesstoken", tokenDto.type() + " " + tokenDto.token());
    }
    protected Cookie adminTokenCookie() {
        TokenDto tokenDto = jwtTokenProvider.createToken(Arrays.asList(Roles.ADMIN));
        return new Cookie("accesstoken", tokenDto.type() + " " + tokenDto.token());
    }

    protected MockHttpServletRequestBuilder get(String url, Object... uriVariables) {
        return MockMvcRequestBuilders.get(url, uriVariables)
                .cookie(userTokenCookie());
    }
    protected MockHttpServletRequestBuilder post(String url, Object bodyData, Object... uriVariables) {
        return MockMvcRequestBuilders.post(url, uriVariables)
                .cookie(adminTokenCookie())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(bodyData));
    }
    protected MockHttpServletRequestBuilder patch(String url, Object bodyData, Object... uriVariables) {
        return MockMvcRequestBuilders.patch(url, uriVariables)
                .cookie(adminTokenCookie())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(bodyData));
    }



}
