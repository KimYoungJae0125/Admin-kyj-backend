package kr.kimyoungjae.admin.domain.projectDescriptions.model.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.projectDescriptions.model.dto.request.ProjectDescriptionsRequestDTO;
import kr.kimyoungjae.admin.domain.projectDescriptions.model.dto.response.ProjectDescriptionsResponseDTO;
import kr.kimyoungjae.admin.domain.projectDescriptions.model.entity.ProjectDescriptionsEntity;
import kr.kimyoungjae.admin.domain.projects.repository.ProjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectDescriptionsMapper implements CommonMapper<ProjectDescriptionsEntity, ProjectDescriptionsRequestDTO, ProjectDescriptionsResponseDTO> {

    private final ProjectsRepository projectsRepository;

    @Override
    public ProjectDescriptionsResponseDTO toResponse(ProjectDescriptionsEntity projectDescriptionsEntity) {
        Long id = projectDescriptionsEntity.getId();
        String content = projectDescriptionsEntity.getContent();
        Integer layoutOrder = projectDescriptionsEntity.getLayoutOrder();

        return new ProjectDescriptionsResponseDTO(id, content, layoutOrder);
    }

    @Override
    public ProjectDescriptionsEntity toEntity(ProjectDescriptionsRequestDTO projectDescriptionsRequestDTO) {

        return ProjectDescriptionsEntity.builder()
                .content(projectDescriptionsRequestDTO.content())
                .layoutOrder(projectDescriptionsRequestDTO.layoutOrder())
                .project(projectsRepository.getProject(projectDescriptionsRequestDTO.projectId()))
                .build();
    }
}