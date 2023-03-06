package kr.kimyoungjae.admin.common.mapper;

import java.util.Arrays;
import java.util.List;

public interface CommonMapper<ENTITY, REQUEST, RESPONSE> {

    RESPONSE toResponse(ENTITY entity);

    default List<RESPONSE> toResponses(List<ENTITY> entities) {
        return entities.stream().map(this::toResponse).toList();
    }
    default List<RESPONSE> toResponses(ENTITY... entities) {
        return this.toResponses(Arrays.asList(entities));
    }

    ENTITY toEntity(REQUEST request);

    default List<ENTITY> toEntities(List<REQUEST> requests) {
        return requests.stream().map(this::toEntity).toList();
    }
    default List<ENTITY> toEntities(REQUEST... requests) {
        return this.toEntities(Arrays.asList(requests));
    }

}
