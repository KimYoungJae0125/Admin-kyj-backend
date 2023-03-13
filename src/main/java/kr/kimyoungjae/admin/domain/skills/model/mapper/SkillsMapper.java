package kr.kimyoungjae.admin.domain.skills.model.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.projects.model.entity.ProjectsEntity;
import kr.kimyoungjae.admin.domain.skills.model.dto.request.SkillsRequestDTO;
import kr.kimyoungjae.admin.domain.skills.model.dto.response.SkillsResponseDTO;
import kr.kimyoungjae.admin.domain.skills.model.entity.SkillsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkillsMapper implements CommonMapper<SkillsEntity, SkillsRequestDTO, SkillsResponseDTO> {

    @Override
    public SkillsResponseDTO toResponse(SkillsEntity skillsEntity) {
        Long id = skillsEntity.getId();
        String name = skillsEntity.getName();
        Integer layoutOrder = skillsEntity.getLayoutOrder();
        return new SkillsResponseDTO(id, name, layoutOrder);
    }

    @Override
    public SkillsEntity toEntity(SkillsRequestDTO skillsRequestDTO) {
        return SkillsEntity.builder()
                .name(skillsRequestDTO.name())
                .layoutOrder(skillsRequestDTO.layoutOrder())
                .project(new ProjectsEntity(skillsRequestDTO.projectId()))
                .build();
    }
}
