package kr.kimyoungjae.admin.domain.certificates.service;

import kr.kimyoungjae.admin.domain.certificates.model.dto.CertificatesResponseDTO;
import kr.kimyoungjae.admin.domain.certificates.model.mapper.CertificatesMapper;
import kr.kimyoungjae.admin.domain.certificates.repository.CertificatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificatesService {

    private final CertificatesRepository certificatesRepository;
    private final CertificatesMapper certificatesMapper;
    public List<CertificatesResponseDTO> findAll() {
        return certificatesMapper.toResponses(certificatesRepository.findAll());
    }
}
