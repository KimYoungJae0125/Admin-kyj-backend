package kr.kimyoungjae.admin.domain.institutions.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "institutions")
public class InstitutionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder
    public InstitutionsEntity(String name) {
        this.name = name;
    }

    public void changeName(String name) {
        this.name = name;
    }

}
