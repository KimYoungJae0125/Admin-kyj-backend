package kr.kimyoungjae.admin.domain.experiences.model.dto;


import java.time.LocalDate;

public record ExperiencesRequestDTO(String institutionName, LocalDate startDate, LocalDate endDate, Long teamId) {
}
