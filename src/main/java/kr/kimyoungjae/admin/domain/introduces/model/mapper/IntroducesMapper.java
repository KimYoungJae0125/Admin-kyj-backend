package kr.kimyoungjae.admin.domain.introduces.model.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.introduces.model.dto.request.IntroducesRequestDTO;
import kr.kimyoungjae.admin.domain.introduces.model.dto.response.IntroducesResponseDTO;
import kr.kimyoungjae.admin.domain.introduces.model.entity.IntroducesEntity;
import org.springframework.stereotype.Component;

@Component
public class IntroducesMapper implements CommonMapper<IntroducesEntity, IntroducesRequestDTO, IntroducesResponseDTO> {

    @Override
    public IntroducesResponseDTO toResponse(IntroducesEntity introducesEntity) {
        Long id = introducesEntity.getId();
        String content = introducesEntity.getContent();
        Integer layoutOrder = introducesEntity.getLayoutOrder();

        return new IntroducesResponseDTO(id, content, layoutOrder);
    }

    @Override
    public IntroducesEntity toEntity(IntroducesRequestDTO introducesRequestDTO) {
        return IntroducesEntity.builder()
                .content(introducesRequestDTO.content())
                .layoutOrder(introducesRequestDTO.layoutOrder())
                .build();
    }
}
