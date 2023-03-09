package kr.kimyoungjae.admin.domain.projectDescriptions.integration;

import kr.kimyoungjae.admin.common.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjectDescriptionsControllerTest extends TestConfig {

    @Autowired
    private MockMvc mockMvc;

    private final String END_POINT = "/v1/projects/{projectId}/descriptions";

    @Test
    void getTest() throws Exception {
        mockMvc.perform(get(END_POINT, 1))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
