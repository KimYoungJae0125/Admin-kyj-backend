package kr.kimyoungjae.admin.domain.myInformations.repository;

import kr.kimyoungjae.admin.domain.myInformations.model.entity.MyInformationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyInformationsRepository extends JpaRepository<MyInformationsEntity, Long> {

    default MyInformationsEntity getMyInformation(Long id) {
        return this.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
