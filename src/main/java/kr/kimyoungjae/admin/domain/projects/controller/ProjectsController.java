package kr.kimyoungjae.admin.domain.projects.controller;

import kr.kimyoungjae.admin.domain.projects.model.dto.request.ProjectsRequestDTO;
import kr.kimyoungjae.admin.domain.projects.model.dto.response.ProjectsResponseDTO;
import kr.kimyoungjae.admin.domain.projects.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
public class ProjectsController {

    private final ProjectsService projectsService;

    @GetMapping
    public List<ProjectsResponseDTO> findAll() {
        return projectsService.findAll();
    }
    @GetMapping("/organization/{organizationId}")
    public List<ProjectsResponseDTO> findAllByOrganization(@PathVariable Long organizationId) {
        return projectsService.findAllByOrganizationId(organizationId);
    }

    @PostMapping
    public ProjectsResponseDTO save(@RequestBody ProjectsRequestDTO projectsRequestDTO) {
        return projectsService.save(projectsRequestDTO);
    }

    @PatchMapping
    public void update() {

    }
    public void delete() {

    }

}
