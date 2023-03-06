package kr.kimyoungjae.admin.domain.myInformations.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "my_informations")
public class MyInformationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 100)
    private String description;

    private Integer layoutOrder;

    @Column(length = 10)
    private String typename;

    @Builder
    public MyInformationsEntity(String name, String description, int layoutOrder, String typename) {
        this.name = name;
        this.description = description;
        this.layoutOrder = layoutOrder;
        this.typename = typename;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeDescription(String description) {
        this.description = description;
    }

    public void switchLayoutOrder(Integer layoutOrder) {
        this.layoutOrder = layoutOrder;
    }

    public void changeType(String typename) {
        this.typename = typename;
    }

}
