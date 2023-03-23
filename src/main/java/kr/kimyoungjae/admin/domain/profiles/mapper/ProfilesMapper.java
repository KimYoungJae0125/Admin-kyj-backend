package kr.kimyoungjae.admin.domain.profiles.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.profiles.model.ProfilesEntity;
import kr.kimyoungjae.admin.domain.profiles.model.ProfilesRequestDTO;
import kr.kimyoungjae.admin.domain.profiles.model.ProfilesResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ProfilesMapper implements CommonMapper<ProfilesEntity, ProfilesRequestDTO, ProfilesResponseDTO> {

    @Override
    public ProfilesEntity toEntity(ProfilesRequestDTO requestDTO) {
        return ProfilesEntity.builder()
                .koreanName(requestDTO.koreanName())
                .englishName(requestDTO.englishName())
                .siteAddress(requestDTO.siteAddress())
                .emailAddress(requestDTO.emailAddress())
                .build();
    }

    @Override
    public ProfilesResponseDTO toResponse(ProfilesEntity entity) {
        Long id = entity.getId();
        String koreanName = entity.getKoreanName();
        String englishName = entity.getEnglishName();
        String jobName = entity.getJobName();
        String siteAddress = entity.getSiteAddress();
        String emailAddress = entity.getEmailAddress();
        return new ProfilesResponseDTO(id, koreanName, englishName, jobName, siteAddress, emailAddress);
    }
}
