package kr.kimyoungjae.admin.domain.projectDescriptions.model.entity;

import jakarta.persistence.*;
import kr.kimyoungjae.admin.domain.projects.model.entity.ProjectsEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "project_descriptions")
public class ProjectDescriptionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Integer layoutOrder;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectsEntity project;

    @Builder
    public ProjectDescriptionsEntity(String content, Integer layoutOrder, ProjectsEntity project) {
        this.content = content;
        this.layoutOrder = layoutOrder;
        this.project = project;
    }


}
