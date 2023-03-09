package kr.kimyoungjae.admin.domain.skills.repository;

import kr.kimyoungjae.admin.domain.skills.model.entity.SkillsEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillsRepository extends JpaRepository<SkillsEntity, Long> {

    default SkillsEntity getSkill(Long skillId) {
        return this.findById(skillId)
                .orElseThrow(RuntimeException::new);
    }

    List<SkillsEntity> findAllByProjectId(Long projectId, Sort sort);

}
