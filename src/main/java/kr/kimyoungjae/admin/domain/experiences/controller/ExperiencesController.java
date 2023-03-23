package kr.kimyoungjae.admin.domain.experiences.controller;

import kr.kimyoungjae.admin.domain.experiences.enums.ExperienceType;
import kr.kimyoungjae.admin.domain.experiences.model.dto.ExperiencesResponseDTO;
import kr.kimyoungjae.admin.domain.experiences.service.ExperiencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/experiences")
@RequiredArgsConstructor
public class ExperiencesController {

    private final ExperiencesService experiencesService;

    @GetMapping
    public List<ExperiencesResponseDTO> findAll() {
        return experiencesService.findAll();
    }
    @GetMapping("/type")
    public List<ExperiencesResponseDTO> findAllByType(@RequestParam ExperienceType type) {
        return experiencesService.findAllByType(type);
    }

}
