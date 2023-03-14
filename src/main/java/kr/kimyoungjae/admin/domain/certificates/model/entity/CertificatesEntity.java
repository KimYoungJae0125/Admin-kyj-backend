package kr.kimyoungjae.admin.domain.certificates.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "certifcates")
public class CertificatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String issuingAuthority;
    private LocalDate certificationDate;

    public CertificatesEntity(Long id) {
        this.id = id;
    }

    @Builder
    public CertificatesEntity(String name, String description, String issuingAuthority, LocalDate certificationDate) {
        this.name = name;
        this.description = description;
        this.issuingAuthority = issuingAuthority;
        this.certificationDate = certificationDate;
    }

    public void changeName(String name) {
        this.name = name;
    }
    public void changeDescription(String description) {
        this.description = description;
    }

}
