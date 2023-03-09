package kr.kimyoungjae.admin.domain.projects.service;

import kr.kimyoungjae.admin.domain.projects.model.dto.request.ProjectsRequestDTO;
import kr.kimyoungjae.admin.domain.projects.model.dto.response.ProjectsResponseDTO;
import kr.kimyoungjae.admin.domain.projects.model.entity.ProjectsEntity;
import kr.kimyoungjae.admin.domain.projects.model.mapper.ProjectsMapper;
import kr.kimyoungjae.admin.domain.projects.repository.ProjectsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static kr.kimyoungjae.admin.common.conditions.CustomSort.layoutSort;

@Service
@RequiredArgsConstructor
public class ProjectsService {

    private final ProjectsRepository projectsRepository;
    private final ProjectsMapper projectsMapper;



    public List<ProjectsResponseDTO> findAll() {
        List<ProjectsEntity> projectsEntities = projectsRepository.findAll(layoutSort());

        return projectsMapper.toResponses(projectsEntities);
    }

    public ProjectsResponseDTO save(ProjectsRequestDTO projectsRequestDTO) {
        ProjectsEntity projectsEntity = projectsMapper.toEntity(projectsRequestDTO);

        return projectsMapper.toResponse(projectsRepository.save(projectsEntity));
    }

    public List<ProjectsResponseDTO> findAllByOrganizationId(Long organizationId) {
        List<ProjectsEntity> projectsEntities = projectsRepository.findAllByOrganizationId(organizationId, layoutSort());

        return projectsMapper.toResponses(projectsEntities);
    }
}
