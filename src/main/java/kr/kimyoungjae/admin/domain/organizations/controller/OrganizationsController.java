package kr.kimyoungjae.admin.domain.organizations.controller;

import kr.kimyoungjae.admin.domain.organizations.model.dto.request.OrganizationsRequestDTO;
import kr.kimyoungjae.admin.domain.organizations.model.dto.response.OrganizationsResponseDTO;
import kr.kimyoungjae.admin.domain.organizations.service.OrganizationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/organizations")
@RequiredArgsConstructor
public class OrganizationsController {
    private final OrganizationsService organizationsService;

    @GetMapping
    public List<OrganizationsResponseDTO> getOrganizationInfoList() {
        return organizationsService.getOrganizationInfoList();
    }
    @GetMapping("/institutions/{institutionId}")
    public List<OrganizationsResponseDTO> getOrganizationInfoListByInstitutionId(@PathVariable Long institutionId) {
        return organizationsService.getOrganizationInfoListByInstitutionId(institutionId);
    }

    @PostMapping
    public OrganizationsResponseDTO saveOrganizationInfo(@RequestBody OrganizationsRequestDTO organizationsRequestDTO) {
        return organizationsService.saveOrganizationInfo(organizationsRequestDTO);
    }

    @PatchMapping("/{organizationId}")
    public OrganizationsResponseDTO updateOrganizationInfo(@RequestBody OrganizationsRequestDTO organizationsRequestDTO, @PathVariable Long organizationId) {
        return organizationsService.updateOrganizationInfo(organizationsRequestDTO, organizationId);
    }

    @DeleteMapping("/{organizationId}")
    public void delete(@PathVariable Long organizationId) {

    }

}
