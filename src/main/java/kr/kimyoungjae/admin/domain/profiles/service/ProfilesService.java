package kr.kimyoungjae.admin.domain.profiles.service;

import kr.kimyoungjae.admin.domain.profiles.mapper.ProfilesMapper;
import kr.kimyoungjae.admin.domain.profiles.model.ProfilesResponseDTO;
import kr.kimyoungjae.admin.domain.profiles.repository.ProfilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfilesService {

    private final ProfilesRepository profilesRepository;
    private final ProfilesMapper profilesMapper;
    public ProfilesResponseDTO getProfile() {
        return profilesMapper.toResponse(profilesRepository.findAll().get(0));
    }
}
