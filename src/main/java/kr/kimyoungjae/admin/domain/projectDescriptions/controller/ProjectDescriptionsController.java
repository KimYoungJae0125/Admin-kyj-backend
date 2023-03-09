package kr.kimyoungjae.admin.domain.projectDescriptions.controller;

import kr.kimyoungjae.admin.domain.projectDescriptions.model.dto.request.ProjectDescriptionsRequestDTO;
import kr.kimyoungjae.admin.domain.projectDescriptions.model.dto.response.ProjectDescriptionsResponseDTO;
import kr.kimyoungjae.admin.domain.projectDescriptions.service.ProjectDescriptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/projects/{projectId}/descriptions")
@RequiredArgsConstructor
public class ProjectDescriptionsController {

    private final ProjectDescriptionsService projectDescriptionsService;

    @GetMapping
    public List<ProjectDescriptionsResponseDTO> get(@PathVariable Long projectId) {
        return projectDescriptionsService.findAll(projectId);
    }

    @PostMapping
    public ProjectDescriptionsResponseDTO post(@RequestBody ProjectDescriptionsRequestDTO projectDescriptionsRequestDTO, @PathVariable Long projectId) {
        return projectDescriptionsService.save(projectDescriptionsRequestDTO, projectId);
    }

    public void patch() {

    }

}
