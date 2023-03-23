package kr.kimyoungjae.admin.domain.teams.model.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.teams.model.dto.request.TeamsRequestDTO;
import kr.kimyoungjae.admin.domain.teams.model.dto.response.TeamsResponseDTO;
import kr.kimyoungjae.admin.domain.teams.model.entity.TeamsEntity;
import org.springframework.stereotype.Component;

@Component
public class TeamsMapper implements CommonMapper<TeamsEntity, TeamsRequestDTO, TeamsResponseDTO> {

    @Override
    public TeamsResponseDTO toResponse(TeamsEntity teamsEntity) {
        Long id = teamsEntity.getId();
        String name = teamsEntity.getName();
        String rank = teamsEntity.getRank();
        return new TeamsResponseDTO(id, name, rank);
    }

    @Override
    public TeamsEntity toEntity(TeamsRequestDTO teamsRequestDTO) {
        return TeamsEntity.builder()
                .name(teamsRequestDTO.name())
                .rank(teamsRequestDTO.rank())
                .build();
    }
}
