package kr.kimyoungjae.admin.domain.institutions.model.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.institutions.model.dto.request.InstitutionsRequestDTO;
import kr.kimyoungjae.admin.domain.institutions.model.dto.response.InstitutionsResponseDTO;
import kr.kimyoungjae.admin.domain.institutions.model.entity.InstitutionsEntity;
import org.springframework.stereotype.Component;

@Component
public class InstitutionsMapper implements CommonMapper<InstitutionsEntity, InstitutionsRequestDTO, InstitutionsResponseDTO> {

    @Override
    public InstitutionsResponseDTO toResponse(InstitutionsEntity institutionsEntity) {
        Long id = institutionsEntity.getId();
        String name = institutionsEntity.getName();

        return new InstitutionsResponseDTO(id, name);
    }

    @Override
    public InstitutionsEntity toEntity(InstitutionsRequestDTO institutionsRequestDTO) {
        return InstitutionsEntity.builder()
                .name(institutionsRequestDTO.name())
                .build();
    }
}
