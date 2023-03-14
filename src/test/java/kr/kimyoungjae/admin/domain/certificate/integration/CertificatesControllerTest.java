package kr.kimyoungjae.admin.domain.certificate.integration;

import kr.kimyoungjae.admin.common.TestConfig;
import kr.kimyoungjae.admin.domain.certificates.model.dto.request.CertificatesRequestDTO;
import kr.kimyoungjae.admin.domain.certificates.model.entity.CertificatesEntity;
import kr.kimyoungjae.admin.domain.certificates.repository.CertificatesRepository;
import kr.kimyoungjae.admin.domain.certificates.service.CertificatesService;
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

import java.time.LocalDate;
import java.util.Arrays;

import static kr.kimyoungjae.admin.common.utils.JsonUtils.objectToJson;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CertificatesControllerTest extends TestConfig {

    @Autowired
    private CertificatesRepository certificatesRepository;

    private String END_POINT = "/v1/certificates";

    @Transactional
    @BeforeAll
    void setUp() {
        certificatesRepository.saveAll(
                Arrays.asList(
                    new CertificatesEntity("자격증", "자격증 설명", "자격증 발급 기관", LocalDate.now())
                  , new CertificatesEntity("자격증2", "자격증 설명2", "자격증 발급 기관2", LocalDate.now())
                )
        );
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
                        jsonPath("$[0].name").value("자격증")
                      , jsonPath("$[0].description").value("자격증 설명")
                      , jsonPath("$[0].issuingAuthority").value("자격증 발급 기관")
                      , jsonPath("$[1].name").value("자격증2")
                      , jsonPath("$[1].description").value("자격증 설명2")
                      , jsonPath("$[1].issuingAuthority").value("자격증 발급 기관2")
                );
    }

    @Test
    @DisplayName("저장 테스트")
    @Transactional
    void postTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
                .cookie(adminTokenCookie())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectToJson(new CertificatesRequestDTO("테스트 자격증", "테스트 자격증 설명", "테스트 자격증 발급 기관", LocalDate.now())))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                          jsonPath("$.name").value("테스트 자격증")
                        , jsonPath("$.description").value("테스트 자격증 설명")
                        , jsonPath("$.issuingAuthority").value("테스트 자격증 발급 기관")
                );
    }

    @Test
    @Transactional
    void patchTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(END_POINT + "/{certificateId}", 1)
                        .cookie(adminTokenCookie())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(new CertificatesRequestDTO("수정 된 자격증", "수정 된 자격증 설명"))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                          jsonPath("$.name").value("수정 된 자격증")
                        , jsonPath("$.description").value("수정 된 자격증 설명")
                );
    }


}
