package kr.kimyoungjae.admin.domain.organizations.integration;

import kr.kimyoungjae.admin.domain.institutions.model.entity.InstitutionsEntity;
import kr.kimyoungjae.admin.domain.institutions.repository.InstitutionsRepository;
import kr.kimyoungjae.admin.domain.organizations.model.dto.request.OrganizationsRequestDTO;
import kr.kimyoungjae.admin.domain.organizations.service.OrganizationsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static kr.kimyoungjae.admin.common.utils.JsonUtils.objectToJson;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InstitutionsRepository institutionsRepository;

    @Autowired
    private OrganizationsService organizationsService;

    private final String END_POINT = "/v1/organizations";

    Long[] institutionIds = new Long[2];

    @BeforeAll
    @Transactional
    void setUp() {
        institutionIds[0] = institutionsRepository.save(InstitutionsEntity.builder().name("테스트 기관1").build()).getId();
        institutionIds[1] = institutionsRepository.save(InstitutionsEntity.builder().name("테스트 기관2").build()).getId();


        organizationsService.saveOrganizationInfo(new OrganizationsRequestDTO("테스트 조직1", "설명1", LocalDate.now(), null, 1, institutionIds[0]));
        organizationsService.saveOrganizationInfo(new OrganizationsRequestDTO("테스트 조직2", "설명2", LocalDate.now(), null, 2, institutionIds[0]));
        organizationsService.saveOrganizationInfo(new OrganizationsRequestDTO("테스트 조직3", "설명3", LocalDate.now(), null, 2, institutionIds[1]));
        organizationsService.saveOrganizationInfo(new OrganizationsRequestDTO("테스트 조직4", "설명4", LocalDate.now(), null, 1, institutionIds[1]));

    }


    @Test
    void getTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$[0].name").value("테스트 조직1")
                      , jsonPath("$[0].description").value("설명1")
                      , jsonPath("$[0].layoutOrder").value(1)
                      , jsonPath("$[0].institution.id").value(institutionIds[0])

                      , jsonPath("$[1].name").value("테스트 조직2")
                      , jsonPath("$[1].description").value("설명2")
                      , jsonPath("$[1].layoutOrder").value(2)
                      , jsonPath("$[1].institution.id").value(institutionIds[0])

                      , jsonPath("$[2].name").value("테스트 조직4")
                      , jsonPath("$[2].description").value("설명4")
                      , jsonPath("$[2].layoutOrder").value(1)
                      , jsonPath("$[2].institution.id").value(institutionIds[1])

                      , jsonPath("$[3].name").value("테스트 조직3")
                      , jsonPath("$[3].description").value("설명3")
                      , jsonPath("$[3].layoutOrder").value(2)
                      , jsonPath("$[3].institution.id").value(institutionIds[1])

                );

    }

    @Test
    void getByInstitutionIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT + "/institutions/{institutionId}", institutionIds[0]))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$[0].name").value("테스트 조직1")
                      , jsonPath("$[0].description").value("설명1")
                      , jsonPath("$[0].layoutOrder").value(1)
                      , jsonPath("$[0].institution.id").value(institutionIds[0])

                      , jsonPath("$[1].name").value("테스트 조직2")
                      , jsonPath("$[1].description").value("설명2")
                      , jsonPath("$[1].layoutOrder").value(2)
                      , jsonPath("$[1].institution.id").value(institutionIds[0])

                      , jsonPath("$[2].name").doesNotExist()
                      , jsonPath("$[2].description").doesNotExist()
                      , jsonPath("$[2].layoutOrder").doesNotExist()
                      , jsonPath("$[2].institution.id").doesNotExist()


                );
    }

    @Test
    @Transactional
    void postTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(new OrganizationsRequestDTO("테스트1", "테스트설명1", LocalDate.now(), null, 1, institutionIds[0]))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.name").value("테스트1")
                      , jsonPath("$.description").value("테스트설명1")
                      , jsonPath("$.layoutOrder").value(1)
                      , jsonPath("$.institution.id").value(institutionIds[0])
                );
    }
    @Test
    @Transactional
    void patchTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch(END_POINT + "/{organizationId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(new OrganizationsRequestDTO("수정 된 이름", "수정 된 설명1", LocalDate.now(), null, null, null))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.name").value("수정 된 이름")
                      , jsonPath("$.description").value("수정 된 설명1")
                      , jsonPath("$.layoutOrder").value(1)
                      , jsonPath("$.institution.id").value(institutionIds[0])
                );
    }



}
