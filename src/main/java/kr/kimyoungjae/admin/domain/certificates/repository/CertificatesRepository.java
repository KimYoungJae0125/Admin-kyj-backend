package kr.kimyoungjae.admin.domain.certificates.repository;

import kr.kimyoungjae.admin.domain.certificates.model.entity.CertificatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificatesRepository extends JpaRepository<CertificatesEntity, Long> {

    default CertificatesEntity getInstitution(Long id) {
        return this.findById(id)
                .orElseThrow(RuntimeException::new);
    }


}
