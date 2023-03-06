package kr.kimyoungjae.admin.domain.myInformations.service;

import kr.kimyoungjae.admin.domain.myInformations.model.dto.request.MyInformationsRequestDTO;
import kr.kimyoungjae.admin.domain.myInformations.model.dto.response.MyInformationsResponseDTO;
import kr.kimyoungjae.admin.domain.myInformations.model.entity.MyInformationsEntity;
import kr.kimyoungjae.admin.domain.myInformations.model.mapper.MyInformationsMapper;
import kr.kimyoungjae.admin.domain.myInformations.repository.MyInformationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static kr.kimyoungjae.admin.common.conditions.CustomSort.layoutSort;

@Service
@RequiredArgsConstructor
public class MyInformationsService {

    private final MyInformationsRepository myInformationsRepository;
    private final MyInformationsMapper myInformationsMapper;

    public List<MyInformationsResponseDTO> getInformationList() {
        return myInformationsMapper.toResponses(myInformationsRepository.findAll(layoutSort()));
    }

    public MyInformationsResponseDTO saveNewInformation(MyInformationsRequestDTO myInformationsRequestDTO) {
        MyInformationsEntity myInformationsEntity = myInformationsMapper.toEntity(myInformationsRequestDTO);

        return myInformationsMapper.toResponse(myInformationsRepository.save(myInformationsEntity));
    }

    public MyInformationsResponseDTO updateInformation(MyInformationsRequestDTO myInformationsRequestDTO, Long myInformationId) {
        MyInformationsEntity myInformationsEntity = myInformationsRepository.getMyInformation(myInformationId);

        String newName = myInformationsRequestDTO.name();
        if(newName != null) {
            myInformationsEntity.changeName(newName);
        }
        String newDescription = myInformationsRequestDTO.description();
        if(newDescription != null)  {
            myInformationsEntity.changeDescription(newDescription);
        }
        Integer newLayoutOrder = myInformationsRequestDTO.layoutOrder();
        if(newLayoutOrder != null) {
            myInformationsEntity.switchLayoutOrder(newLayoutOrder);
        }
        String newType = myInformationsRequestDTO.typeName();
        if(newType != null) {
            myInformationsEntity.changeType(newType);
        }

        return myInformationsMapper.toResponse(myInformationsRepository.save(myInformationsEntity));
    }

}
