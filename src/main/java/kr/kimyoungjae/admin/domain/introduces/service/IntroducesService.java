package kr.kimyoungjae.admin.domain.introduces.service;

import kr.kimyoungjae.admin.domain.introduces.model.dto.request.IntroducesRequestDTO;
import kr.kimyoungjae.admin.domain.introduces.model.dto.response.IntroducesResponseDTO;
import kr.kimyoungjae.admin.domain.introduces.model.entity.IntroducesEntity;
import kr.kimyoungjae.admin.domain.introduces.model.mapper.IntroducesMapper;
import kr.kimyoungjae.admin.domain.introduces.repository.IntroducesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static kr.kimyoungjae.admin.common.conditions.CustomSort.layoutSort;

@Service
@RequiredArgsConstructor
public class IntroducesService {

    private final IntroducesRepository introducesRepository;
    private final IntroducesMapper introducesMapper;

    public List<IntroducesResponseDTO> getIntroducesInfoList() {
        return introducesMapper.toResponses(introducesRepository.findAll(layoutSort()));
    }

    public IntroducesResponseDTO saveIntroduce(IntroducesRequestDTO introducesRequestDTO) {
        IntroducesEntity introducesEntity = introducesMapper.toEntity(introducesRequestDTO);
        return introducesMapper.toResponse(introducesRepository.save(introducesEntity));
    }

    public IntroducesResponseDTO updateIntroduce(IntroducesRequestDTO introducesRequestDTO, Long introduceId) {
        IntroducesEntity introducesEntity = introducesRepository.getIntroduce(introduceId);

        String newContent = introducesRequestDTO.content();
        if(newContent != null) {
            introducesEntity.changeContent(newContent);
        }

        Integer switchLayoutOrder = introducesRequestDTO.layoutOrder();
        if(switchLayoutOrder != null) {
            introducesEntity.switchLayoutOrder(switchLayoutOrder);
        }

        return introducesMapper.toResponse(introducesEntity);
    }

}
