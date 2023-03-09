package kr.kimyoungjae.admin.domain.teams.integration;

import kr.kimyoungjae.admin.common.TestConfig;
import kr.kimyoungjae.admin.domain.teams.model.dto.request.TeamsRequestDTO;
import kr.kimyoungjae.admin.domain.teams.service.TeamsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TeamsControllerTest extends TestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeamsService teamsService;

    private final String END_POINT = "/v1/teams";

    @BeforeAll
    @Transactional
    void setUp() {
        teamsService.save(new TeamsRequestDTO("팀1"));
        teamsService.save(new TeamsRequestDTO("팀2"));
    }

    @Test
    void getTest() throws Exception {
        mockMvc.perform(get(END_POINT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$[0].name").value("팀1")
                      , jsonPath("$[1].name").value("팀2")
                );

    }

    @Test
    @Transactional
    void postTest() throws Exception {

    }


}
