package kr.kimyoungjae.admin.domain.experience_descriptions.model.entity;

import jakarta.persistence.*;
import kr.kimyoungjae.admin.domain.experiences.model.entity.ExperiencesEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "experience_descriptions")
public class ExperienceDescriptionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "experience_id")
    private ExperiencesEntity experience;

    public ExperienceDescriptionsEntity(Long id) {
        this.id = id;
    }

    @Builder
    public ExperienceDescriptionsEntity(String content, ExperiencesEntity experience) {
        this.content = content;
        this.experience = experience;
    }


}
