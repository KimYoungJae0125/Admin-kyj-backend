package kr.kimyoungjae.admin.domain.introduces.controller;

import kr.kimyoungjae.admin.domain.introduces.model.dto.request.IntroducesRequestDTO;
import kr.kimyoungjae.admin.domain.introduces.model.dto.response.IntroducesResponseDTO;
import kr.kimyoungjae.admin.domain.introduces.service.IntroducesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/introduces")
@RequiredArgsConstructor
public class IntroducesController {

    private final IntroducesService introducesService;

    @GetMapping
    public List<IntroducesResponseDTO> getIntroducesInfoList() {

        return introducesService.getIntroducesInfoList();
    }

    @PostMapping
    public IntroducesResponseDTO saveIntroduce(@RequestBody IntroducesRequestDTO introducesRequestDTO) {
        return introducesService.saveIntroduce(introducesRequestDTO);
    }
    @PatchMapping("/{introduceId}")
    public IntroducesResponseDTO updateIntroduce(@RequestBody IntroducesRequestDTO introducesRequestDTO, @PathVariable Long introduceId) {
        return introducesService.updateIntroduce(introducesRequestDTO, introduceId);
    }

    @DeleteMapping("/{introduceId}")
    public void delete(@PathVariable Long introduceId) {

    }

}
