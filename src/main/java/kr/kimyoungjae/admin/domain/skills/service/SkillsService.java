package kr.kimyoungjae.admin.domain.skills.service;

import kr.kimyoungjae.admin.domain.skills.model.dto.request.SkillsRequestDTO;
import kr.kimyoungjae.admin.domain.skills.model.dto.response.SkillsResponseDTO;
import kr.kimyoungjae.admin.domain.skills.model.entity.SkillsEntity;
import kr.kimyoungjae.admin.domain.skills.model.mapper.SkillsMapper;
import kr.kimyoungjae.admin.domain.skills.repository.SkillsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.kimyoungjae.admin.common.conditions.CustomSort.layoutSort;

@Service
@RequiredArgsConstructor
public class SkillsService {

    private final SkillsRepository skillsRepository;
    private final SkillsMapper skillsMapper;

    public List<SkillsResponseDTO> findAllByProjectId(Long projectId) {
        return skillsMapper.toResponses(skillsRepository.findAllByProjectId(projectId, layoutSort()));
    }

    @Transactional
    public SkillsResponseDTO save(SkillsRequestDTO skillsRequestDTO) {
        SkillsEntity skillsEntity = skillsMapper.toEntity(skillsRequestDTO);

        return skillsMapper.toResponse(skillsRepository.save(skillsEntity));
    }

    @Transactional
    public SkillsResponseDTO update(SkillsRequestDTO skillsRequestDTO, Long skillId) {
        SkillsEntity skillsEntity = skillsRepository.getSkill(skillId);

        Integer switchLayoutOrder = skillsRequestDTO.layoutOrder();
        if(switchLayoutOrder != null) {
            skillsEntity.switchLayoutOrder(switchLayoutOrder);
        }

        return skillsMapper.toResponse(skillsEntity);
    }



}
