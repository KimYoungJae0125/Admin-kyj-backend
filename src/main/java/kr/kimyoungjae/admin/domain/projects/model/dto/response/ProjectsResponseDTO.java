package kr.kimyoungjae.admin.domain.projects.model.dto.response;

import kr.kimyoungjae.admin.domain.projectDescriptions.model.dto.response.ProjectDescriptionsResponseDTO;
import kr.kimyoungjae.admin.domain.skills.model.dto.response.SkillsResponseDTO;
import kr.kimyoungjae.admin.domain.teams.model.dto.response.TeamsResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record ProjectsResponseDTO(Long id, String name, Integer layoutOrder, LocalDate startDate, LocalDate endDate, TeamsResponseDTO team, List<ProjectDescriptionsResponseDTO> descriptions, List<SkillsResponseDTO> skills) {

}
