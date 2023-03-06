package kr.kimyoungjae.admin.domain.institutions.controller;

import kr.kimyoungjae.admin.domain.institutions.model.dto.request.InstitutionsRequestDTO;
import kr.kimyoungjae.admin.domain.institutions.model.dto.response.InstitutionsResponseDTO;
import kr.kimyoungjae.admin.domain.institutions.service.InstitutionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/institutions")
@RequiredArgsConstructor
public class InstitutionsController {
    private final InstitutionsService institutionsService;

    @GetMapping
    public List<InstitutionsResponseDTO> getInstitutionsInfoList() {
        return institutionsService.getInstitutionsInfoList();
    }

    @PostMapping
    public InstitutionsResponseDTO saveInstitutionInfo(@RequestBody InstitutionsRequestDTO institutionsRequestDTO) {
        return institutionsService.saveInstitutionInfo(institutionsRequestDTO);
    }

    @PatchMapping("/{institutionId}")
    public InstitutionsResponseDTO updateInstitutionInfo(@RequestBody InstitutionsRequestDTO institutionsRequestDTO, @PathVariable Long institutionId) {
        return institutionsService.updateInstitutionInfo(institutionsRequestDTO, institutionId);
    }
    @DeleteMapping
    public void delete() {

    }

}
