package kr.kimyoungjae.admin.domain.organizations.repository;

import kr.kimyoungjae.admin.domain.organizations.model.entity.OrganizationsEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganizationsRepository extends JpaRepository<OrganizationsEntity, Long> {

    default OrganizationsEntity getOrganization(Long organizationId) {
        return this.findById(organizationId)
                .orElseThrow(RuntimeException::new);
    }

    List<OrganizationsEntity> findAllByInstitutionId(Long institutionId, Sort sort);


}
