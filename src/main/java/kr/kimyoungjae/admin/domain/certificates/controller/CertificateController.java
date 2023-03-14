package kr.kimyoungjae.admin.domain.certificates.controller;

import kr.kimyoungjae.admin.domain.certificates.model.dto.request.CertificatesRequestDTO;
import kr.kimyoungjae.admin.domain.certificates.model.dto.response.CertificatesResponseDTO;
import kr.kimyoungjae.admin.domain.certificates.service.CertificatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/certificates")
@RequiredArgsConstructor
public class CertificateController {
    private final CertificatesService institutionsService;

    @GetMapping
    public List<CertificatesResponseDTO> getCertificatesInfoList() {
        return institutionsService.getCertificatesInfoList();
    }

    @PostMapping
    public CertificatesResponseDTO saveCertificateInfo(@RequestBody CertificatesRequestDTO certificatesRequestDTO) {
        return institutionsService.saveCertificateInfo(certificatesRequestDTO);
    }

    @PatchMapping("/{certificateId}")
    public CertificatesResponseDTO updateCertificateInfo(@RequestBody CertificatesRequestDTO certificatesRequestDTO, @PathVariable Long certificateId) {
        return institutionsService.updateCertificateInfo(certificatesRequestDTO, certificateId);
    }
    @DeleteMapping
    public void delete() {

    }

}
