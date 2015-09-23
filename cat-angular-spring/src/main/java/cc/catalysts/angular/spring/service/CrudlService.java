package cc.catalysts.angular.spring.service;

import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.PageDto;

import java.io.Serializable;

/**
 * @author Klaus Lehner
 */
public interface CrudlService<ID extends Serializable, LIST_DTO, DETAIL_DTO, SEARCH_REQUEST extends SearchRequest> {

    DETAIL_DTO create(DETAIL_DTO fghData);

    void delete(ID entityId);

    DETAIL_DTO get(ID entityId);

    PageDto<LIST_DTO> list(SEARCH_REQUEST searchRequest);

    DETAIL_DTO update(ID entityId, DETAIL_DTO fghData);
}
