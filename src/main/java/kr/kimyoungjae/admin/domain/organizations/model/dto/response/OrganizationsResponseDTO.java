package kr.kimyoungjae.admin.domain.organizations.model.dto.response;

import kr.kimyoungjae.admin.domain.institutions.model.dto.response.InstitutionsResponseDTO;
import kr.kimyoungjae.admin.domain.projects.model.dto.response.ProjectsResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record OrganizationsResponseDTO(Long id, String name, String description, LocalDate startDate, LocalDate endDate, Integer layoutOrder, InstitutionsResponseDTO institution, List<ProjectsResponseDTO> projects) {

}
