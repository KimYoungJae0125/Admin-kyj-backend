package kr.kimyoungjae.admin.domain.experience_descriptions.model.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.experience_descriptions.model.dto.ExperienceDescriptionsRequestDTO;
import kr.kimyoungjae.admin.domain.experience_descriptions.model.dto.ExperienceDescriptionsResponseDTO;
import kr.kimyoungjae.admin.domain.experience_descriptions.model.entity.ExperienceDescriptionsEntity;
import kr.kimyoungjae.admin.domain.experiences.model.entity.ExperiencesEntity;
import org.springframework.stereotype.Component;

@Component
public class ExperienceDescriptionsMapper implements CommonMapper<ExperienceDescriptionsEntity, ExperienceDescriptionsRequestDTO, ExperienceDescriptionsResponseDTO> {

    @Override
    public ExperienceDescriptionsResponseDTO toResponse(ExperienceDescriptionsEntity experienceDescriptionsEntity) {
        Long id = experienceDescriptionsEntity.getId();
        String content = experienceDescriptionsEntity.getContent();
        return new ExperienceDescriptionsResponseDTO(id, content);
    }

    @Override
    public ExperienceDescriptionsEntity toEntity(ExperienceDescriptionsRequestDTO experienceDescriptionsRequestDTO) {
        return ExperienceDescriptionsEntity.builder()
                .content(experienceDescriptionsRequestDTO.content())
                .experience(new ExperiencesEntity(experienceDescriptionsRequestDTO.experienceId()))
                .build();
    }
}
