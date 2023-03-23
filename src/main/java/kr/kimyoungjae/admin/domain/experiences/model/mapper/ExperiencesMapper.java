package kr.kimyoungjae.admin.domain.experiences.model.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.experience_descriptions.model.dto.ExperienceDescriptionsResponseDTO;
import kr.kimyoungjae.admin.domain.experience_descriptions.model.mapper.ExperienceDescriptionsMapper;
import kr.kimyoungjae.admin.domain.experiences.enums.ExperienceType;
import kr.kimyoungjae.admin.domain.experiences.model.dto.ExperiencesRequestDTO;
import kr.kimyoungjae.admin.domain.experiences.model.dto.ExperiencesResponseDTO;
import kr.kimyoungjae.admin.domain.experiences.model.entity.ExperiencesEntity;
import kr.kimyoungjae.admin.domain.skills.model.dto.response.SkillsResponseDTO;
import kr.kimyoungjae.admin.domain.skills.model.mapper.SkillsMapper;
import kr.kimyoungjae.admin.domain.teams.model.dto.response.TeamsResponseDTO;
import kr.kimyoungjae.admin.domain.teams.model.entity.TeamsEntity;
import kr.kimyoungjae.admin.domain.teams.model.mapper.TeamsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExperiencesMapper implements CommonMapper<ExperiencesEntity, ExperiencesRequestDTO, ExperiencesResponseDTO> {

    private final ExperienceDescriptionsMapper experienceDescriptionsMapper;
    private final TeamsMapper teamsMapper;
    private final SkillsMapper skillsMapper;

    @Override
    public ExperiencesResponseDTO toResponse(ExperiencesEntity experiencesEntity) {
        Long id = experiencesEntity.getId();
        String institutionName = experiencesEntity.getInstitutionName();
        LocalDate startDate = experiencesEntity.getStartDate();
        LocalDate endDate = experiencesEntity.getEndDate();
        ExperienceType type = experiencesEntity.getType();
        TeamsResponseDTO team = null;
        TeamsEntity teamsEntity = experiencesEntity.getTeam();
        if(teamsEntity != null) {
            team = teamsMapper.toResponse(experiencesEntity.getTeam());
        }
        List<ExperienceDescriptionsResponseDTO> descriptions = experienceDescriptionsMapper.toResponses(experiencesEntity.getDescriptions());
        List<SkillsResponseDTO> skills = skillsMapper.toResponses(experiencesEntity.getSkills());
        return new ExperiencesResponseDTO(id, institutionName, startDate, endDate, type, team, descriptions, skills);
    }

    @Override
    public ExperiencesEntity toEntity(ExperiencesRequestDTO experiencesRequestDTO) {
        return ExperiencesEntity.builder()
                .institutionName(experiencesRequestDTO.institutionName())
                .startDate(experiencesRequestDTO.startDate())
                .endDate(experiencesRequestDTO.endDate())
                .team(new TeamsEntity(experiencesRequestDTO.teamId()))
                .build();
    }
}
