package kr.kimyoungjae.admin.domain.certificates.model.entity;

import jakarta.persistence.*;
import kr.kimyoungjae.admin.domain.experiences.model.entity.ExperiencesEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "certificates")
public class CertificatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String institutionName;

    private LocalDate startDate;

    private LocalDate endDate;


    public CertificatesEntity(Long id) {
        this.id = id;
    }

    @Builder
    public CertificatesEntity(String name, String institutionName, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.institutionName = institutionName;
        this.startDate = startDate;
        this.endDate = endDate;
    }


}
