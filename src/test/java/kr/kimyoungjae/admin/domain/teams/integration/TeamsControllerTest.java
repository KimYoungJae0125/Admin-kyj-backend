package kr.kimyoungjae.admin.domain.teams.integration;

import kr.kimyoungjae.admin.common.TestConfig;
import kr.kimyoungjae.admin.domain.teams.model.dto.request.TeamsRequestDTO;
import kr.kimyoungjae.admin.domain.teams.model.entity.TeamsEntity;
import kr.kimyoungjae.admin.domain.teams.repository.TeamsRepository;
import kr.kimyoungjae.admin.domain.teams.service.TeamsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeamsControllerTest extends TestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeamsRepository teamsRepository;

    private final String END_POINT = "/v1/teams";

    @BeforeAll
    @Transactional
    void setUp() {
        teamsRepository.saveAll(
                Arrays.asList(
                      TeamsEntity.builder().name("팀1").rank("직급").build()
                    , TeamsEntity.builder().name("팀2").rank("직급").build()
                )
        );
    }

    @Test
    void getTest() throws Exception {
        mockMvc.perform(get(END_POINT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$[0].name").value("팀1")
                      , jsonPath("$[0].rank").value("직급")
                      , jsonPath("$[1].name").value("팀2")
                      , jsonPath("$[1].rank").value("직급")
                );

    }

    @Test
    @Transactional
    void postTest() throws Exception {

    }


}
