package kr.kimyoungjae.admin.domain.skills.model.entity;

import jakarta.persistence.*;
import kr.kimyoungjae.admin.domain.experiences.model.entity.ExperiencesEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "skills")
public class SkillsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer layoutOrder;

    @ManyToOne
    @JoinColumn(name = "experience_id")
    private ExperiencesEntity experience;

    @Builder
    public SkillsEntity(String name, Integer layoutOrder, ExperiencesEntity experience) {
        this.name = name;
        this.layoutOrder = layoutOrder;
        this.experience = experience;
    }

    public void switchLayoutOrder(Integer layoutOrder) {
        this.layoutOrder = layoutOrder;
    }

}
