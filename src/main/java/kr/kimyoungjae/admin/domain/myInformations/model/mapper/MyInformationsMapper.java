package kr.kimyoungjae.admin.domain.myInformations.model.mapper;

import kr.kimyoungjae.admin.common.mapper.CommonMapper;
import kr.kimyoungjae.admin.domain.myInformations.model.dto.request.MyInformationsRequestDTO;
import kr.kimyoungjae.admin.domain.myInformations.model.dto.response.MyInformationsResponseDTO;
import kr.kimyoungjae.admin.domain.myInformations.model.entity.MyInformationsEntity;
import org.springframework.stereotype.Component;

@Component
public class MyInformationsMapper implements CommonMapper<MyInformationsEntity, MyInformationsRequestDTO, MyInformationsResponseDTO> {

    public MyInformationsResponseDTO toResponse(MyInformationsEntity myInformationsEntity) {
        Long id = myInformationsEntity.getId();
        String name = myInformationsEntity.getName();
        String description = myInformationsEntity.getDescription();
        Integer layoutOrder = myInformationsEntity.getLayoutOrder();
        String typeName = myInformationsEntity.getTypename();

        return new MyInformationsResponseDTO(id, name, description, layoutOrder, typeName);
    }

    public MyInformationsEntity toEntity(MyInformationsRequestDTO myInformationsRequestDTO) {
        return MyInformationsEntity.builder()
                .name(myInformationsRequestDTO.name())
                .description(myInformationsRequestDTO.description())
                .layoutOrder(myInformationsRequestDTO.layoutOrder())
                .typename(myInformationsRequestDTO.typeName())
                .build();
    }
}
