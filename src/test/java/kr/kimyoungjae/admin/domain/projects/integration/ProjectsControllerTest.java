package kr.kimyoungjae.admin.domain.projects.integration;

import kr.kimyoungjae.admin.common.TestConfig;
import kr.kimyoungjae.admin.domain.institutions.model.dto.request.InstitutionsRequestDTO;
import kr.kimyoungjae.admin.domain.institutions.service.InstitutionsService;
import kr.kimyoungjae.admin.domain.organizations.model.dto.request.OrganizationsRequestDTO;
import kr.kimyoungjae.admin.domain.organizations.service.OrganizationsService;
import kr.kimyoungjae.admin.domain.projects.model.dto.request.ProjectsRequestDTO;
import kr.kimyoungjae.admin.domain.teams.model.dto.request.TeamsRequestDTO;
import kr.kimyoungjae.admin.domain.teams.service.TeamsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static java.time.LocalDate.now;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjectsControllerTest extends TestConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InstitutionsService institutionsService;
    @Autowired
    private OrganizationsService organizationsService;
    @Autowired
    private TeamsService teamsService;

    private final String END_POINT  = "/v1/projects";


    @Test
    @Transactional
    void save() throws Exception {
        Long institutionId = institutionsService.saveInstitutionInfo(new InstitutionsRequestDTO("교육")).id();
        Long teamId = teamsService.save(new TeamsRequestDTO("팀")).id();
        Long organizationId = organizationsService.saveOrganizationInfo(new OrganizationsRequestDTO("조직", "설명", now(), null, 1, institutionId)).id();

        ProjectsRequestDTO projectsRequestDTO = new ProjectsRequestDTO("project이름", 1, now(), null, teamId, organizationId);
        mockMvc.perform(post(END_POINT, projectsRequestDTO))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get(END_POINT))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
