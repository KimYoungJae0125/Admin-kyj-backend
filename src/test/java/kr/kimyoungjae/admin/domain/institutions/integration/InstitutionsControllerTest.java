package kr.kimyoungjae.admin.domain.institutions.integration;

import kr.kimyoungjae.admin.common.TestConfig;
import kr.kimyoungjae.admin.domain.institutions.model.dto.request.InstitutionsRequestDTO;
import kr.kimyoungjae.admin.domain.institutions.service.InstitutionsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static kr.kimyoungjae.admin.common.utils.JsonUtils.objectToJson;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("기관 정보 테스트")
public class InstitutionsControllerTest extends TestConfig {

    @Autowired
    private InstitutionsService institutionsService;

    private String END_POINT = "/v1/institutions";

    @Transactional
    @BeforeAll
    void setUp() {
        institutionsService.saveInstitutionInfo(new InstitutionsRequestDTO("교육"));
        institutionsService.saveInstitutionInfo(new InstitutionsRequestDTO("기관"));
    }

    @Test
    @DisplayName("정보 조회 테스트")
    void getTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT)
                        .cookie(userTokenCookie())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$[0].name").value("교육")
                      , jsonPath("$[1].name").value("기관")
                );
    }

    @Test
    @DisplayName("저장 테스트")
    @Transactional
    void postTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .cookie(adminTokenCookie())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectToJson(new InstitutionsRequestDTO("테스트 기관")))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.name").value("테스트 기관")
                );
    }

    @Test
    @Transactional
    void patchTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(END_POINT + "/{institutionId}", 1)
                        .cookie(adminTokenCookie())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(new InstitutionsRequestDTO("수정 기관"))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.name").value("수정 기관")
                      , jsonPath("$.name").value(not("교육"))
                );
    }


}
