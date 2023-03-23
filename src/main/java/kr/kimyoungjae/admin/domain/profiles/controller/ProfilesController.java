package kr.kimyoungjae.admin.domain.profiles.controller;

import kr.kimyoungjae.admin.domain.profiles.model.ProfilesResponseDTO;
import kr.kimyoungjae.admin.domain.profiles.service.ProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/profiles")
@RequiredArgsConstructor
public class ProfilesController {

    private final ProfilesService profilesService;

    @GetMapping
    public ProfilesResponseDTO getProfile() {
        return profilesService.getProfile();
    }

}
