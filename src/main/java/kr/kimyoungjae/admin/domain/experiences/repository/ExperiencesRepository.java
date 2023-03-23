package kr.kimyoungjae.admin.domain.experiences.repository;

import kr.kimyoungjae.admin.domain.experiences.model.entity.ExperiencesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperiencesRepository extends JpaRepository<ExperiencesEntity, Long> {
}
