package kr.kimyoungjae.admin.domain.organizations.model.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import kr.kimyoungjae.admin.domain.institutions.model.entity.InstitutionsEntity;
import kr.kimyoungjae.admin.domain.projects.model.entity.ProjectsEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "organizations")
public class OrganizationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(updatable = false)
    private LocalDate startDate;
    private LocalDate endDate;

    private Integer layoutOrder;
    @ManyToOne
    @JoinColumn(name = "institution_id")
    private InstitutionsEntity institution;

    @OneToMany(mappedBy = "organization")
    @OrderBy(value = "layoutOrder")
    private List<ProjectsEntity> projects = new ArrayList<>();

    public OrganizationsEntity(Long id) {
        this.id = id;
    }

    @Builder
    public OrganizationsEntity(String name, String description, LocalDate startDate, LocalDate endDate, Integer layoutOrder, InstitutionsEntity institution) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.layoutOrder = layoutOrder;
        this.institution = institution;
    }

    public void changeName(String name) {
        this.name = name;
    }
    public void changeDescription(String description) {
        this.description = description;
    }

    public void leaveOrganization(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void switchLayoutOrder(Integer layoutOrder) {
        this.layoutOrder = layoutOrder;
    }

    public void changeInstitution(InstitutionsEntity institution) {
        this.institution = institution;
    }




}
