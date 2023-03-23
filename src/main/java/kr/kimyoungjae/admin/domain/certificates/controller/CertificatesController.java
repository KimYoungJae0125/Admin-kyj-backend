package kr.kimyoungjae.admin.domain.certificates.controller;

import kr.kimyoungjae.admin.domain.certificates.model.dto.CertificatesResponseDTO;
import kr.kimyoungjae.admin.domain.certificates.service.CertificatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/certificates")
@RequiredArgsConstructor
public class CertificatesController {

    private final CertificatesService certificatesService;

    @GetMapping
    public List<CertificatesResponseDTO> findAll() {
        return certificatesService.findAll();
    }

}
