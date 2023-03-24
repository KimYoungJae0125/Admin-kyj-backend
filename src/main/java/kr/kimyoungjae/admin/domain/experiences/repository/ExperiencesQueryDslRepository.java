package kr.kimyoungjae.admin.domain.experiences.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.kimyoungjae.admin.common.querydsl.QueryDSLRepository;
import kr.kimyoungjae.admin.domain.experiences.enums.ExperienceType;
import kr.kimyoungjae.admin.domain.experiences.model.entity.ExperiencesEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.kimyoungjae.admin.domain.experience_descriptions.model.entity.QExperienceDescriptionsEntity.experienceDescriptionsEntity;
import static kr.kimyoungjae.admin.domain.experiences.model.entity.QExperiencesEntity.experiencesEntity;
import static kr.kimyoungjae.admin.domain.teams.model.entity.QTeamsEntity.teamsEntity;

@Repository
@RequiredArgsConstructor
public class ExperiencesQueryDslRepository implements QueryDSLRepository<ExperiencesEntity> {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ExperiencesEntity> findAll() {

        return this.findAll(null);
    }
    public List<ExperiencesEntity> findAll(ExperienceType type) {

        return jpaQueryFactory
                .selectFrom(experiencesEntity)
                .leftJoin(experiencesEntity.descriptions, experienceDescriptionsEntity).fetchJoin()
                .leftJoin(experiencesEntity.team, teamsEntity).fetchJoin()
                .orderBy(
                        experiencesEntity.type.asc()
                        , experiencesEntity.endDate.desc()
                        , experienceDescriptionsEntity.id.asc()
                )
                .where(
                        type == null ? null : experiencesEntity.type.eq(type)
                )
                .fetch();
    }

}
