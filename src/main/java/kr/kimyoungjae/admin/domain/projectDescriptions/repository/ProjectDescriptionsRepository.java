package kr.kimyoungjae.admin.domain.projectDescriptions.repository;

import kr.kimyoungjae.admin.domain.projectDescriptions.model.entity.ProjectDescriptionsEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDescriptionsRepository extends JpaRepository<ProjectDescriptionsEntity, Long> {

    List<ProjectDescriptionsEntity> findAllByProjectId(Long projectsId, Sort sort);

}
