package kr.kimyoungjae.admin.domain.experiences.model.entity;

import jakarta.persistence.*;
import kr.kimyoungjae.admin.domain.experience_descriptions.model.entity.ExperienceDescriptionsEntity;
import kr.kimyoungjae.admin.domain.experiences.enums.ExperienceType;
import kr.kimyoungjae.admin.domain.skills.model.entity.SkillsEntity;
import kr.kimyoungjae.admin.domain.teams.model.entity.TeamsEntity;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "experiences")
public class ExperiencesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String institutionName;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private ExperienceType type;

    @OneToMany(mappedBy = "experience")
    private List<ExperienceDescriptionsEntity> descriptions = new ArrayList<>();

    @OneToMany(mappedBy = "experience")
    private List<SkillsEntity> skills = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamsEntity team;


    public ExperiencesEntity(Long id) {
        this.id = id;
    }

    @Builder
    public ExperiencesEntity(String institutionName, LocalDate startDate, LocalDate endDate, ExperienceType type, TeamsEntity team) {
        this.institutionName = institutionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.team = team;
    }


}
