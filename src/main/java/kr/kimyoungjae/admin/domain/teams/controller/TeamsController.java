package kr.kimyoungjae.admin.domain.teams.controller;

import kr.kimyoungjae.admin.domain.teams.model.dto.request.TeamsRequestDTO;
import kr.kimyoungjae.admin.domain.teams.model.dto.response.TeamsResponseDTO;
import kr.kimyoungjae.admin.domain.teams.service.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/teams")
@RequiredArgsConstructor
public class TeamsController {

    private final TeamsService teamsService;

    @GetMapping
    public List<TeamsResponseDTO> getTeamsInfoList() {
        return teamsService.getTeamsInfoList();
    }

    @PostMapping
    public TeamsResponseDTO save(@RequestBody TeamsRequestDTO teamsRequestDTO) {
        return teamsService.save(teamsRequestDTO);
    }

    @PatchMapping
    public void update() {

    }

}
