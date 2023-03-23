package kr.kimyoungjae.admin.domain.experiences.model.dto;


import kr.kimyoungjae.admin.domain.experience_descriptions.model.dto.ExperienceDescriptionsResponseDTO;
import kr.kimyoungjae.admin.domain.experiences.enums.ExperienceType;
import kr.kimyoungjae.admin.domain.skills.model.dto.response.SkillsResponseDTO;
import kr.kimyoungjae.admin.domain.teams.model.dto.response.TeamsResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record ExperiencesResponseDTO(Long id, String institutionName, LocalDate startDate, LocalDate endDate, ExperienceType type, TeamsResponseDTO team, List<ExperienceDescriptionsResponseDTO> descriptions, List<SkillsResponseDTO> skills) {
}
