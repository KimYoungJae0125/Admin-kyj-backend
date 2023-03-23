package kr.kimyoungjae.admin.domain.experience_descriptions.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/experiences/{experienceId}/descriptions")
@RequiredArgsConstructor
public class ExperienceDescriptionsController {
}
