package kr.kimyoungjae.admin.domain.projectDescriptions.service;

import kr.kimyoungjae.admin.domain.projectDescriptions.model.dto.request.ProjectDescriptionsRequestDTO;
import kr.kimyoungjae.admin.domain.projectDescriptions.model.dto.response.ProjectDescriptionsResponseDTO;
import kr.kimyoungjae.admin.domain.projectDescriptions.model.entity.ProjectDescriptionsEntity;
import kr.kimyoungjae.admin.domain.projectDescriptions.model.mapper.ProjectDescriptionsMapper;
import kr.kimyoungjae.admin.domain.projectDescriptions.repository.ProjectDescriptionsRepository;
import kr.kimyoungjae.admin.domain.projects.model.dto.request.ProjectsRequestDTO;
import kr.kimyoungjae.admin.domain.projects.model.dto.response.ProjectsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.kimyoungjae.admin.common.conditions.CustomSort.layoutSort;

@Service
@RequiredArgsConstructor
public class ProjectDescriptionsService {

    private final ProjectDescriptionsRepository projectDescriptionsRepository;
    private final ProjectDescriptionsMapper projectDescriptionsMapper;


    public List<ProjectDescriptionsResponseDTO> findAll(Long projectId) {

        return projectDescriptionsMapper.toResponses(projectDescriptionsRepository.findAllByProjectId(projectId, layoutSort()));
    }


    @Transactional
    public ProjectDescriptionsResponseDTO save(ProjectDescriptionsRequestDTO projectDescriptionsRequestDTO, Long projectId) {
        ProjectDescriptionsEntity projectDescriptionsEntity = projectDescriptionsMapper.toEntity(projectDescriptionsRequestDTO.setProjectId(projectId));
        return projectDescriptionsMapper.toResponse(projectDescriptionsRepository.save(projectDescriptionsEntity));
    }
}
