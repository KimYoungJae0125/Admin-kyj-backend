package kr.kimyoungjae.admin.domain.projects.model.entity;

import jakarta.persistence.*;
import kr.kimyoungjae.admin.domain.organizations.model.entity.OrganizationsEntity;
import kr.kimyoungjae.admin.domain.projectDescriptions.model.entity.ProjectDescriptionsEntity;
import kr.kimyoungjae.admin.domain.skills.model.entity.SkillsEntity;
import kr.kimyoungjae.admin.domain.teams.model.entity.TeamsEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "projects")
public class ProjectsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    private Integer layoutOrder;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamsEntity team;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private OrganizationsEntity organization;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @OrderBy(value = "layoutOrder")
    private List<ProjectDescriptionsEntity> descriptions = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    @OrderBy(value = "layoutOrder")
    private List<SkillsEntity> skills = new ArrayList<>();

    @Builder
    public ProjectsEntity(String name, Integer layoutOrder, LocalDate startDate, LocalDate endDate, TeamsEntity team, OrganizationsEntity organization) {
        this.name = name;
        this.layoutOrder = layoutOrder;
        this.startDate = startDate;
        this.endDate = endDate;
        this.team = team;
        this.organization = organization;
    }

}
