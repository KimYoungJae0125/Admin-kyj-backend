package kr.kimyoungjae.admin.domain.teams.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "teams")
public class TeamsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public TeamsEntity(Long id) {
        this.id = id;
    }

    @Builder
    public TeamsEntity(String name) {
        this.name = name;
    }


}
