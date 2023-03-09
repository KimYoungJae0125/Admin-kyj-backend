package kr.kimyoungjae.admin.domain.myInformations.controller;

import kr.kimyoungjae.admin.domain.myInformations.model.dto.request.MyInformationsRequestDTO;
import kr.kimyoungjae.admin.domain.myInformations.model.dto.response.MyInformationsResponseDTO;
import kr.kimyoungjae.admin.domain.myInformations.service.MyInformationsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/my-informations")
@RequiredArgsConstructor
public class MyInformationsController {

    private final MyInformationsService myInformationsService;

    @GetMapping
    public List<MyInformationsResponseDTO> getInformationList() {
        return myInformationsService.getInformationList();
    }

    @PostMapping
    public MyInformationsResponseDTO saveNewInformation(@RequestBody MyInformationsRequestDTO myInformationsRequestDTO) {
        return myInformationsService.saveNewInformation(myInformationsRequestDTO);
    }

    @PatchMapping("/{myInformationId}")
    public MyInformationsResponseDTO updateInformation(@RequestBody MyInformationsRequestDTO myInformationsRequestDTO, @PathVariable Long myInformationId) {
        return myInformationsService.updateInformation(myInformationsRequestDTO, myInformationId);
    }

    @DeleteMapping("/{myInformationId}")
    public void delete(@PathVariable Long myInformationId) {

    }

}
