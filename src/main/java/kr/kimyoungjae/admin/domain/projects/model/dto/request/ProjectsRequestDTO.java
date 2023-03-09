package kr.kimyoungjae.admin.domain.projects.model.dto.request;

import java.time.LocalDate;

public record ProjectsRequestDTO(String name, Integer layoutOrder, LocalDate startDate, LocalDate endDate, Long teamId, Long organizationId) {

}
