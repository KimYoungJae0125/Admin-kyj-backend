package kr.kimyoungjae.admin.domain.teams.service;

import kr.kimyoungjae.admin.domain.teams.model.dto.request.TeamsRequestDTO;
import kr.kimyoungjae.admin.domain.teams.model.dto.response.TeamsResponseDTO;
import kr.kimyoungjae.admin.domain.teams.model.entity.TeamsEntity;
import kr.kimyoungjae.admin.domain.teams.model.mapper.TeamsMapper;
import kr.kimyoungjae.admin.domain.teams.repository.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamsService {

    private final TeamsRepository teamsRepository;
    private final TeamsMapper teamsMapper;

    public List<TeamsResponseDTO> getTeamsInfoList() {
        return teamsMapper.toResponses(teamsRepository.findAll());
    }

    public TeamsResponseDTO findById(Long id) {
        return teamsMapper.toResponse(teamsRepository.getTeam(id));
    }

    public TeamsResponseDTO save(TeamsRequestDTO teamsRequestDTO) {
        TeamsEntity teamsEntity = teamsMapper.toEntity(teamsRequestDTO);

        return teamsMapper.toResponse(teamsRepository.save(teamsEntity));
    }

}
