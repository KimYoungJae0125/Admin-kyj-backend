package kr.kimyoungjae.admin.domain.certificates.model.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.certificates.model.dto.request.CertificatesRequestDTO;
import kr.kimyoungjae.admin.domain.certificates.model.dto.response.CertificatesResponseDTO;
import kr.kimyoungjae.admin.domain.certificates.model.entity.CertificatesEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CertificatesMapper implements CommonMapper<CertificatesEntity, CertificatesRequestDTO, CertificatesResponseDTO> {

    @Override
    public CertificatesResponseDTO toResponse(CertificatesEntity certificatesEntity) {
        Long id = certificatesEntity.getId();
        String name = certificatesEntity.getName();
        String description = certificatesEntity.getDescription();
        String issuingAuthority = certificatesEntity.getIssuingAuthority();
        LocalDate certificationDate = certificatesEntity.getCertificationDate();

        return new CertificatesResponseDTO(id, name, description, issuingAuthority, certificationDate);
    }

    @Override
    public CertificatesEntity toEntity(CertificatesRequestDTO certificatesRequestDTO) {
        return CertificatesEntity.builder()
                .name(certificatesRequestDTO.name())
                .description(certificatesRequestDTO.description())
                .issuingAuthority(certificatesRequestDTO.issuingAuthority())
                .certificationDate(certificatesRequestDTO.certificationDate())
                .build();
    }
}
