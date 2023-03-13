package kr.kimyoungjae.admin.domain.organizations.service;

import kr.kimyoungjae.admin.domain.institutions.model.entity.InstitutionsEntity;
import kr.kimyoungjae.admin.domain.organizations.model.dto.request.OrganizationsRequestDTO;
import kr.kimyoungjae.admin.domain.organizations.model.dto.response.OrganizationsResponseDTO;
import kr.kimyoungjae.admin.domain.organizations.model.entity.OrganizationsEntity;
import kr.kimyoungjae.admin.domain.organizations.model.mapper.OrganizationsMapper;
import kr.kimyoungjae.admin.domain.organizations.repository.OrganizationQueryDslRepository;
import kr.kimyoungjae.admin.domain.organizations.repository.OrganizationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static kr.kimyoungjae.admin.common.conditions.CustomSort.layoutSort;

@Service
@RequiredArgsConstructor
public class OrganizationsService {

    private final OrganizationsRepository organizationsRepository;
    private final OrganizationQueryDslRepository organizationQueryDslRepository;
    private final OrganizationsMapper organizationsMapper;

    public List<OrganizationsResponseDTO> getOrganizationInfoList() {
        return organizationsMapper.toResponses(organizationQueryDslRepository.findAll());
    }

    public OrganizationsResponseDTO saveOrganizationInfo(OrganizationsRequestDTO organizationsRequestDTO) {
        OrganizationsEntity organizationsEntity = organizationsMapper.toEntity(organizationsRequestDTO);

        return organizationsMapper.toResponse(organizationsRepository.save(organizationsEntity));
    }

    public OrganizationsResponseDTO updateOrganizationInfo(OrganizationsRequestDTO organizationsRequestDTO, Long organizationId) {
        OrganizationsEntity organizationsEntity = organizationsRepository.getOrganization(organizationId);

        String newName = organizationsRequestDTO.name();
        if(newName != null) {
            organizationsEntity.changeName(newName);
        }
        String newDescription = organizationsRequestDTO.description();
        if(newDescription != null) {
            organizationsEntity.changeDescription(newDescription);
        }
        LocalDate leaveDate = organizationsRequestDTO.endDate();
        if(leaveDate != null) {
            organizationsEntity.leaveOrganization(leaveDate);
        }
        Integer layoutOrder = organizationsRequestDTO.layoutOrder();
        if(layoutOrder != null) {
            organizationsEntity.switchLayoutOrder(layoutOrder);
        }

        Long changeInstitutionId = organizationsRequestDTO.institutionId();
        if(changeInstitutionId != null) {
            organizationsEntity.changeInstitution(new InstitutionsEntity(changeInstitutionId));
        }


        return organizationsMapper.toResponse(organizationsEntity);
    }

    public List<OrganizationsResponseDTO> getOrganizationInfoListByInstitutionId(Long institutionId) {
        return organizationsMapper.toResponses(organizationQueryDslRepository.findAll(institutionId));
    }
}
