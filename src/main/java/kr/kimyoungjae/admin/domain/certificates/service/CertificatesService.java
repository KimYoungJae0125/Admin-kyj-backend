package kr.kimyoungjae.admin.domain.certificates.service;

import kr.kimyoungjae.admin.domain.certificates.model.dto.request.CertificatesRequestDTO;
import kr.kimyoungjae.admin.domain.certificates.model.dto.response.CertificatesResponseDTO;
import kr.kimyoungjae.admin.domain.certificates.model.entity.CertificatesEntity;
import kr.kimyoungjae.admin.domain.certificates.model.mapper.CertificatesMapper;
import kr.kimyoungjae.admin.domain.certificates.repository.CertificatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificatesService {

    private final CertificatesRepository certificatesRepository;
    private final CertificatesMapper certificatesMapper;


    public List<CertificatesResponseDTO> getCertificatesInfoList() {
        return certificatesMapper.toResponses(certificatesRepository.findAll());
    }


    @Transactional
    public CertificatesResponseDTO saveCertificateInfo(CertificatesRequestDTO certificatesRequestDTO) {
        CertificatesEntity certificatesEntity = certificatesMapper.toEntity(certificatesRequestDTO);
        return certificatesMapper.toResponse(certificatesRepository.save(certificatesEntity));
    }

    @Transactional
    public CertificatesResponseDTO updateCertificateInfo(CertificatesRequestDTO certificatesRequestDTO, Long certificateId) {
        CertificatesEntity institutionsEntity = certificatesRepository.getInstitution(certificateId);
        String newName = certificatesRequestDTO.name();
        if(newName != null) {
            institutionsEntity.changeName(newName);
        }
        String newDescription = certificatesRequestDTO.description();
        if(newDescription != null) {
            institutionsEntity.changeDescription(newDescription);
        }

        return certificatesMapper.toResponse(institutionsEntity);
    }

}
