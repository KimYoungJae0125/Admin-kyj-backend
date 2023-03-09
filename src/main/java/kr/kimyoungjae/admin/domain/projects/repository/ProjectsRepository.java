package kr.kimyoungjae.admin.domain.projects.repository;

import kr.kimyoungjae.admin.domain.projects.model.entity.ProjectsEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<ProjectsEntity, Long> {

    default ProjectsEntity getProject(Long id) {
        return this.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    List<ProjectsEntity> findAllByOrganizationId(Long organizationId, Sort layoutSort);
}
