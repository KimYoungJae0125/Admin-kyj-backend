package kr.kimyoungjae.admin.domain.teams.repository;

import kr.kimyoungjae.admin.domain.teams.model.entity.TeamsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamsRepository extends JpaRepository<TeamsEntity, Long> {

    default TeamsEntity getTeam(Long id) {
        return this.findById(id)
                .orElseThrow(RuntimeException::new);
    }


}
