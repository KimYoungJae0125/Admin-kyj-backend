package kr.kimyoungjae.admin.domain.introduces.repository;

import kr.kimyoungjae.admin.domain.introduces.model.entity.IntroducesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntroducesRepository extends JpaRepository<IntroducesEntity, Long> {

    default IntroducesEntity getIntroduce(Long id) {
        return this.findById(id)
                .orElseThrow(RuntimeException::new);
    }


}
