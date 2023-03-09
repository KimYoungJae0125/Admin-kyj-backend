package kr.kimyoungjae.admin.domain.skills.controller;

import kr.kimyoungjae.admin.domain.skills.model.dto.request.SkillsRequestDTO;
import kr.kimyoungjae.admin.domain.skills.model.dto.response.SkillsResponseDTO;
import kr.kimyoungjae.admin.domain.skills.service.SkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1//project/{projectId}/skills")
@RequiredArgsConstructor
public class SkillsController {
    private final SkillsService skillsService;

    @GetMapping
    public List<SkillsResponseDTO> findAllByProjectId(@PathVariable Long projectId) {
        return skillsService.findAllByProjectId(projectId);
    }

    @PostMapping
    public SkillsResponseDTO post(@RequestBody SkillsRequestDTO skillsRequestDTO) {
        return skillsService.save(skillsRequestDTO);
    }


    @PatchMapping("/{skillId}")
    public SkillsResponseDTO patch(@RequestBody SkillsRequestDTO skillsRequestDTO, @PathVariable Long skillId) {
        return skillsService.update(skillsRequestDTO, skillId);
    }

}
