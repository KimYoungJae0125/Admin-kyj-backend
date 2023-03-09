package kr.kimyoungjae.admin.domain.projectDescriptions.model.dto.request;

public record ProjectDescriptionsRequestDTO(String content, Integer layoutOrder, Long projectId) {

    public ProjectDescriptionsRequestDTO setProjectId(Long projectId) {
        return new ProjectDescriptionsRequestDTO(this.content(), this.layoutOrder(), projectId);
    }

}
