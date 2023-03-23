package kr.kimyoungjae.admin.domain.certificates.repository;

import kr.kimyoungjae.admin.domain.certificates.model.entity.CertificatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificatesRepository extends JpaRepository<CertificatesEntity, Long> {
}
