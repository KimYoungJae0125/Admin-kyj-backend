package kr.kimyoungjae.admin.domain.projects.model.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.organizations.model.entity.OrganizationsEntity;
import kr.kimyoungjae.admin.domain.projectDescriptions.model.dto.response.ProjectDescriptionsResponseDTO;
import kr.kimyoungjae.admin.domain.projectDescriptions.model.mapper.ProjectDescriptionsMapper;
import kr.kimyoungjae.admin.domain.projects.model.dto.request.ProjectsRequestDTO;
import kr.kimyoungjae.admin.domain.projects.model.dto.response.ProjectsResponseDTO;
import kr.kimyoungjae.admin.domain.projects.model.entity.ProjectsEntity;
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
public class ProjectsMapper implements CommonMapper<ProjectsEntity, ProjectsRequestDTO, ProjectsResponseDTO> {

    private final TeamsMapper teamsMapper;
    private final ProjectDescriptionsMapper projectDescriptionsMapper;
    private final SkillsMapper skillsMapper;


    @Override
    public ProjectsResponseDTO toResponse(ProjectsEntity projectsEntity) {
        Long id = projectsEntity.getId();
        String name = projectsEntity.getName();
        Integer layoutOrder = projectsEntity.getLayoutOrder();
        LocalDate startDate = projectsEntity.getStartDate();
        LocalDate endDate = projectsEntity.getEndDate();
        TeamsResponseDTO team = teamsMapper.toResponse(projectsEntity.getTeam());
        List<ProjectDescriptionsResponseDTO> projectDescriptions = projectDescriptionsMapper.toResponses(projectsEntity.getDescriptions());
        List<SkillsResponseDTO> skills = skillsMapper.toResponses(projectsEntity.getSkills());

        return new ProjectsResponseDTO(id, name, layoutOrder, startDate, endDate, team, projectDescriptions, skills);
    }

    @Override
    public ProjectsEntity toEntity(ProjectsRequestDTO projectsRequestDTO) {

        return ProjectsEntity.builder()
                .name(projectsRequestDTO.name())
                .layoutOrder(projectsRequestDTO.layoutOrder())
                .startDate(projectsRequestDTO.startDate())
                .endDate(projectsRequestDTO.endDate())
                .team(new TeamsEntity(projectsRequestDTO.teamId()))
                .organization(new OrganizationsEntity(projectsRequestDTO.organizationId()))
                .build();
    }
}
