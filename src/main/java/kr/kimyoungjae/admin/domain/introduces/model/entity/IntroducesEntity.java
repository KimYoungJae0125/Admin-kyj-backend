package kr.kimyoungjae.admin.domain.introduces.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "introduces")
public class IntroducesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String content;

    private Integer layoutOrder;

    @Builder
    public IntroducesEntity(String content, Integer layoutOrder) {
        this.content = content;
        this.layoutOrder = layoutOrder;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void switchLayoutOrder(Integer layoutOrder) {
        this.layoutOrder = layoutOrder;
    }

}
