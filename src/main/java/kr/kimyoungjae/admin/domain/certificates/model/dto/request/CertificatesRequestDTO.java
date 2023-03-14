package kr.kimyoungjae.admin.domain.certificates.model.dto.request;

import java.time.LocalDate;

public record CertificatesRequestDTO(String name, String description, String issuingAuthority, LocalDate certificationDate) {
    public CertificatesRequestDTO(String name, String description) {
        this(name, description, null, null);
    }

}
