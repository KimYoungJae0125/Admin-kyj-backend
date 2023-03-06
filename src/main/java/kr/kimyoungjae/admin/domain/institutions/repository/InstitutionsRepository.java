package kr.kimyoungjae.admin.domain.institutions.repository;

import kr.kimyoungjae.admin.domain.institutions.model.entity.InstitutionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionsRepository extends JpaRepository<InstitutionsEntity, Long> {

    default InstitutionsEntity getInstitution(Long id) {
        return this.findById(id)
                .orElseThrow(RuntimeException::new);
    }


}
