package kr.kimyoungjae.admin.domain.profiles.model;

public record ProfilesRequestDTO(String koreanName
                                , String englishName
                                , String jobName
                                , String siteAddress
                                , String emailAddress) {
}
