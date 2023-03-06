package kr.kimyoungjae.admin.domain.organizations.model.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.institutions.model.dto.response.InstitutionsResponseDTO;
import kr.kimyoungjae.admin.domain.institutions.model.entity.InstitutionsEntity;
import kr.kimyoungjae.admin.domain.institutions.model.mapper.InstitutionsMapper;
import kr.kimyoungjae.admin.domain.institutions.repository.InstitutionsRepository;
import kr.kimyoungjae.admin.domain.organizations.model.dto.request.OrganizationsRequestDTO;
import kr.kimyoungjae.admin.domain.organizations.model.dto.response.OrganizationsResponseDTO;
import kr.kimyoungjae.admin.domain.organizations.model.entity.OrganizationsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class OrganizationsMapper implements CommonMapper<OrganizationsEntity, OrganizationsRequestDTO, OrganizationsResponseDTO> {

    private final InstitutionsRepository institutionsRepository;
    private final InstitutionsMapper institutionsMapper;

    @Override
    public OrganizationsResponseDTO toResponse(OrganizationsEntity organizationsEntity) {
        Long id = organizationsEntity.getId();
        String name = organizationsEntity.getName();
        String description = organizationsEntity.getDescription();
        LocalDate startDate = organizationsEntity.getStartDate();
        LocalDate endDate =  organizationsEntity.getEndDate();
        Integer layoutOrder = organizationsEntity.getLayoutOrder();
        InstitutionsResponseDTO institution = institutionsMapper.toResponse(organizationsEntity.getInstitution());

        return new OrganizationsResponseDTO(id, name, description, startDate, endDate, layoutOrder, institution);
    }

    @Override
    public OrganizationsEntity toEntity(OrganizationsRequestDTO organizationsRequestDTO) {
        return OrganizationsEntity.builder()
                .name(organizationsRequestDTO.name())
                .description(organizationsRequestDTO.description())
                .startDate(organizationsRequestDTO.startDate())
                .endDate(organizationsRequestDTO.endDate())
                .layoutOrder(organizationsRequestDTO.layoutOrder())
                .institution(institutionsRepository.getInstitution(organizationsRequestDTO.institutionId()))
                .build();
    }

    public InstitutionsEntity getInstitution(Long institutionId) {
        return institutionsRepository.getInstitution(institutionId);
    }

}
