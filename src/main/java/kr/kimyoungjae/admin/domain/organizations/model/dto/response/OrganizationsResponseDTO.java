package kr.kimyoungjae.admin.domain.organizations.model.dto.response;

import kr.kimyoungjae.admin.domain.institutions.model.dto.response.InstitutionsResponseDTO;

import java.time.LocalDate;

public record OrganizationsResponseDTO(Long id, String name, String description, LocalDate startDate, LocalDate endDate, Integer layoutOrder, InstitutionsResponseDTO institution) {

}
