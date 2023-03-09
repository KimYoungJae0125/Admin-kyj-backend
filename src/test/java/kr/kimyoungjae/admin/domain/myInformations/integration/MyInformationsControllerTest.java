package kr.kimyoungjae.admin.domain.myInformations.integration;

import kr.kimyoungjae.admin.common.TestConfig;
import kr.kimyoungjae.admin.domain.myInformations.model.dto.request.MyInformationsRequestDTO;
import kr.kimyoungjae.admin.domain.myInformations.service.MyInformationsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("내 정보 API 테스트")
public class MyInformationsControllerTest extends TestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MyInformationsService myInformationsService;

    private final String END_POINT = "/v1/myInformations";

    @BeforeAll
    @Transactional
    void setUp() {
        myInformationsService.saveNewInformation(new MyInformationsRequestDTO("Profile", "프로필", 2, "text"));
        myInformationsService.saveNewInformation(new MyInformationsRequestDTO("Image", "이미지", 1, "image"));
        myInformationsService.saveNewInformation(new MyInformationsRequestDTO("Link", "링크", 4, "link"));
        myInformationsService.saveNewInformation(new MyInformationsRequestDTO("Email", "이메일", 3, "email"));
    }


    @Test
    void getTest() throws Exception {
        mockMvc.perform(get(END_POINT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                       jsonPath("$[0].name").value("Image")
                     , jsonPath("$[0].description").value("이미지")
                     , jsonPath("$[0].layoutOrder").value(1)
                     , jsonPath("$[0].typeName").value("image")

                     , jsonPath("$[1].name").value("Profile")
                     , jsonPath("$[1].description").value("프로필")
                     , jsonPath("$[1].layoutOrder").value(2)
                     , jsonPath("$[1].typeName").value("text")

                     , jsonPath("$[2].name").value("Email")
                     , jsonPath("$[2].description").value("이메일")
                     , jsonPath("$[2].layoutOrder").value(3)
                     , jsonPath("$[2].typeName").value("email")

                     , jsonPath("$[3].name").value("Link")
                     , jsonPath("$[3].description").value("링크")
                     , jsonPath("$[3].layoutOrder").value(4)
                     , jsonPath("$[3].typeName").value("link")
                );
    }

    @Test
    @Transactional
    void postTest() throws  Exception {
        mockMvc.perform(post(END_POINT, new MyInformationsRequestDTO("test", "테스트", 0, "test")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.name").value("test")
                      , jsonPath("$.description").value("테스트")
                      , jsonPath("$.layoutOrder").value(0)
                      , jsonPath("$.typeName").value("test")

                );
    }
    @Test
    @Transactional
    void patchTest() throws  Exception {
        mockMvc.perform(patch(END_POINT + "/{myInformationId}", new MyInformationsRequestDTO("test", "테스트", 0, "test"), 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.name").value("test")
                      , jsonPath("$.description").value("테스트")
                      , jsonPath("$.layoutOrder").value(0)
                      , jsonPath("$.typeName").value("test")

                );
    }

}
