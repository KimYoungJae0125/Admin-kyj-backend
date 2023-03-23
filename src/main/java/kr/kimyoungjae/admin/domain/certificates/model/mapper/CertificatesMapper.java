package kr.kimyoungjae.admin.domain.certificates.model.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.certificates.model.dto.CertificatesRequestDTO;
import kr.kimyoungjae.admin.domain.certificates.model.dto.CertificatesResponseDTO;
import kr.kimyoungjae.admin.domain.certificates.model.entity.CertificatesEntity;
import kr.kimyoungjae.admin.domain.experience_descriptions.model.dto.ExperienceDescriptionsRequestDTO;
import kr.kimyoungjae.admin.domain.experience_descriptions.model.dto.ExperienceDescriptionsResponseDTO;
import kr.kimyoungjae.admin.domain.experience_descriptions.model.entity.ExperienceDescriptionsEntity;
import kr.kimyoungjae.admin.domain.experiences.model.entity.ExperiencesEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CertificatesMapper implements CommonMapper<CertificatesEntity, CertificatesRequestDTO, CertificatesResponseDTO> {

    @Override
    public CertificatesResponseDTO toResponse(CertificatesEntity certificatesEntity) {
        Long id = certificatesEntity.getId();
        String name = certificatesEntity.getName();
        String institutionName = certificatesEntity.getInstitutionName();
        LocalDate startDate = certificatesEntity.getStartDate();
        LocalDate endDate = certificatesEntity.getEndDate();
        return new CertificatesResponseDTO(id, name, institutionName, startDate, endDate);
    }

    @Override
    public CertificatesEntity toEntity(CertificatesRequestDTO certificatesRequestDTO) {
        return CertificatesEntity.builder()
                .name(certificatesRequestDTO.name())
                .institutionName(certificatesRequestDTO.institutionName())
                .startDate(certificatesRequestDTO.startDate())
                .endDate(certificatesRequestDTO.endDate())
                .build();
    }
}
