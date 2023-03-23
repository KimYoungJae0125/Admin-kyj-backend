package kr.kimyoungjae.admin.domain.experiences.service;

import kr.kimyoungjae.admin.domain.experiences.enums.ExperienceType;
import kr.kimyoungjae.admin.domain.experiences.model.dto.ExperiencesResponseDTO;
import kr.kimyoungjae.admin.domain.experiences.model.mapper.ExperiencesMapper;
import kr.kimyoungjae.admin.domain.experiences.repository.ExperiencesQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperiencesService {
    private final ExperiencesQueryDslRepository experiencesQueryDslRepository;
    private final ExperiencesMapper experiencesMapper;
    public List<ExperiencesResponseDTO> findAll() {
        return experiencesMapper.toResponses(experiencesQueryDslRepository.findAll());
    }

    public List<ExperiencesResponseDTO> findAllByType(ExperienceType type) {
        return experiencesMapper.toResponses(experiencesQueryDslRepository.findAll(type));
    }
}
