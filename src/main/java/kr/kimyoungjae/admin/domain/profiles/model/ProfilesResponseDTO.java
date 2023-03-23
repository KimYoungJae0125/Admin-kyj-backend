package kr.kimyoungjae.admin.domain.profiles.model;

public record ProfilesResponseDTO(Long id, String koreanName, String englishName, String jobName, String siteAddress, String emailAddress) {
}
