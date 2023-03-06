package kr.kimyoungjae.admin.domain.institutions.service;

import kr.kimyoungjae.admin.domain.institutions.model.dto.request.InstitutionsRequestDTO;
import kr.kimyoungjae.admin.domain.institutions.model.dto.response.InstitutionsResponseDTO;
import kr.kimyoungjae.admin.domain.institutions.model.entity.InstitutionsEntity;
import kr.kimyoungjae.admin.domain.institutions.model.mapper.InstitutionsMapper;
import kr.kimyoungjae.admin.domain.institutions.repository.InstitutionsRepository;
import kr.kimyoungjae.admin.domain.introduces.model.entity.IntroducesEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstitutionsService {

    private final InstitutionsRepository institutionsRepository;
    private final InstitutionsMapper institutionsMapper;


    public List<InstitutionsResponseDTO> getInstitutionsInfoList() {
        return institutionsMapper.toResponses(institutionsRepository.findAll());
    }


    @Transactional
    public InstitutionsResponseDTO saveInstitutionInfo(InstitutionsRequestDTO institutionsRequestDTO) {
        InstitutionsEntity institutionsEntity = institutionsMapper.toEntity(institutionsRequestDTO);
        return institutionsMapper.toResponse(institutionsRepository.save(institutionsEntity));
    }

    public InstitutionsResponseDTO updateInstitutionInfo(InstitutionsRequestDTO institutionsRequestDTO, Long institutionId) {
        InstitutionsEntity institutionsEntity = institutionsRepository.getInstitution(institutionId);
        String newName = institutionsRequestDTO.name();
        if(newName != null) {
            institutionsEntity.changeName(newName);
        }

        return institutionsMapper.toResponse(institutionsRepository.save(institutionsEntity));
    }

}
