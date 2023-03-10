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

    private String name;

    private String description;

    private Integer layoutOrder;

    @Column(length = 10)
    private String typeName;

    @Builder
    public MyInformationsEntity(String name, String description, int layoutOrder, String typeName) {
        this.name = name;
        this.description = description;
        this.layoutOrder = layoutOrder;
        this.typeName = typeName;
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

    public void changeType(String typeName) {
        this.typeName = typeName;
    }

}
