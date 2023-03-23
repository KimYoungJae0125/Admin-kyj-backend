package kr.kimyoungjae.admin.domain.experiences;

import kr.kimyoungjae.admin.domain.experiences.enums.ExperienceType;
import kr.kimyoungjae.admin.domain.experiences.model.dto.ExperiencesResponseDTO;
import kr.kimyoungjae.admin.domain.experiences.model.entity.ExperiencesEntity;
import kr.kimyoungjae.admin.domain.experiences.model.mapper.ExperiencesMapper;
import kr.kimyoungjae.admin.domain.experiences.repository.ExperiencesRepository;
import kr.kimyoungjae.admin.domain.teams.model.entity.TeamsEntity;
import kr.kimyoungjae.admin.domain.teams.repository.TeamsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ExperiencesTest {

    @Autowired
    private ExperiencesRepository experiencesRepository;

    @Autowired
    private TeamsRepository teamsRepository;

    @Autowired
    private ExperiencesMapper experiencesMapper;

    @Test
    @Transactional
    void test() {

//        experiencesRepository.saveAll(
//                Arrays.asList(
//                        ExperiencesEntity.builder()
//                                .name("이름")
//                                .startDate(LocalDate.now())
//                                .type(ExperienceType.OTHER)
////                                .team(teamsRepository.save(TeamsEntity.builder().name("테스트").build()))
//                                .build()
//                )
//        );

        List<ExperiencesResponseDTO> experiences = experiencesMapper.toResponses(experiencesRepository.findAll());
        experiences.stream().forEach(System.out::println);

    }

}
