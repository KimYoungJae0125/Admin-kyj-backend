package kr.kimyoungjae.admin.domain.certificates.model.dto;


import java.time.LocalDate;

public record CertificatesResponseDTO(Long id, String name, String institutionName, LocalDate startDate, LocalDate endDate) {
}
