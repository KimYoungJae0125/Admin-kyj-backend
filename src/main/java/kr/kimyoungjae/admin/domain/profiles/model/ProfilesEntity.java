package kr.kimyoungjae.admin.domain.profiles.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "profiles")
public class ProfilesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String koreanName;
    private String englishName;
    private String jobName;
    private String siteAddress;
    private String emailAddress;

    public ProfilesEntity(Long id) {
        this.id = id;
    }
    @Builder
    public ProfilesEntity(String koreanName, String englishName, String jobName, String siteAddress, String emailAddress) {
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.jobName = jobName;
        this.siteAddress = siteAddress;
        this.emailAddress = emailAddress;
    }

}
