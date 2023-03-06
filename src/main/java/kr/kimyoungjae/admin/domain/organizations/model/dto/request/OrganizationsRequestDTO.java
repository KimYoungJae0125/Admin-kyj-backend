package kr.kimyoungjae.admin.domain.organizations.model.dto.request;

import java.time.LocalDate;

public record OrganizationsRequestDTO(String name, String description, LocalDate startDate, LocalDate endDate, Integer layoutOrder, Long institutionId) {

}
