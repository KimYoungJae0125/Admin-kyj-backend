package kr.kimyoungjae.admin.domain.organizations.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.kimyoungjae.admin.common.querydsl.QueryDSLRepository;
import kr.kimyoungjae.admin.domain.organizations.model.entity.OrganizationsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.kimyoungjae.admin.domain.institutions.model.entity.QInstitutionsEntity.institutionsEntity;
import static kr.kimyoungjae.admin.domain.organizations.model.entity.QOrganizationsEntity.organizationsEntity;
import static kr.kimyoungjae.admin.domain.projects.model.entity.QProjectsEntity.projectsEntity;
import static kr.kimyoungjae.admin.domain.teams.model.entity.QTeamsEntity.teamsEntity;

@Repository
@RequiredArgsConstructor
public class OrganizationQueryDslRepository implements QueryDSLRepository<OrganizationsEntity> {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OrganizationsEntity> findAll() {

        return jpaQueryFactory
                .selectFrom(organizationsEntity)
                .leftJoin(organizationsEntity.institution, institutionsEntity).fetchJoin()
                .leftJoin(organizationsEntity.projects, projectsEntity).fetchJoin()
                .leftJoin(projectsEntity.team, teamsEntity).fetchJoin()
                .orderBy(
                        organizationsEntity.institution.id.asc()
                      , organizationsEntity.layoutOrder.asc()
                      , projectsEntity.layoutOrder.asc()
                )
                .fetch();
    }
    public List<OrganizationsEntity> findAllByInstitutionId(Long institutionId) {

        return jpaQueryFactory
                .selectFrom(organizationsEntity)
                .leftJoin(organizationsEntity.institution, institutionsEntity).fetchJoin()
                .leftJoin(organizationsEntity.projects, projectsEntity).fetchJoin()
                .leftJoin(projectsEntity.team, teamsEntity).fetchJoin()
                .orderBy(
                        organizationsEntity.institution.id.asc()
                      , organizationsEntity.layoutOrder.asc()
                      , projectsEntity.layoutOrder.asc()
                )
                .where(
                       organizationsEntity.institution.id.eq(institutionId)
                )
                .fetch();
    }
}
