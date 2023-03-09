package kr.kimyoungjae.admin.domain.introduces.integration;

import kr.kimyoungjae.admin.common.TestConfig;
import kr.kimyoungjae.admin.domain.introduces.model.dto.request.IntroducesRequestDTO;
import kr.kimyoungjae.admin.domain.introduces.service.IntroducesService;
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
@DisplayName("자기 소개 정보 테스트")
public class IntroducesControllerTest extends TestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IntroducesService introducesService;

    private final String END_POINT = "/v1/introduces";

    @BeforeAll
    @Transactional
    void setUp() {
        introducesService.saveIntroduce(new IntroducesRequestDTO("자기소개1", 1));
        introducesService.saveIntroduce(new IntroducesRequestDTO("자기소개2", 3));
        introducesService.saveIntroduce(new IntroducesRequestDTO("자기소개3", 2));
    }


    @Test
    void getTest() throws Exception {
        mockMvc.perform(get(END_POINT))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @Transactional
    void postTest() throws Exception {
        mockMvc.perform(post(END_POINT, new IntroducesRequestDTO("자기소개4", 4)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.content").value("자기소개4")
                      , jsonPath("$.layoutOrder").value(4)
                );
    }

    @Test
    void patchTest() throws Exception {
        mockMvc.perform(patch(END_POINT + "/{introduceId}", new IntroducesRequestDTO("수정 된 자기소개1", 4), 1)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.content").value("수정 된 자기소개1")
                      , jsonPath("$.layoutOrder").value(4)
                );
    }


}
