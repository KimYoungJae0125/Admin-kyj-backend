package kr.kimyoungjae.admin.domain.certificates.model.dto.response;

import java.time.LocalDate;

public record CertificatesResponseDTO(Long id, String name, String description, String issuingAuthority, LocalDate certificationDate) {
}
