package kr.kimyoungjae.admin.domain.skills.model.entity;

import jakarta.persistence.*;
import kr.kimyoungjae.admin.domain.projects.model.entity.ProjectsEntity;
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

    @Column(columnDefinition = "varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL")
    private String name;
    private Integer layoutOrder;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectsEntity project;

    @Builder
    public SkillsEntity(String name, Integer layoutOrder, ProjectsEntity project) {
        this.name = name;
        this.layoutOrder = layoutOrder;
        this.project = project;
    }

    public void switchLayoutOrder(Integer layoutOrder) {
        this.layoutOrder = layoutOrder;
    }

}
