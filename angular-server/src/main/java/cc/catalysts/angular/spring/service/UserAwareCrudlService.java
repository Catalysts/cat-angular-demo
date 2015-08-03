package cc.catalysts.angular.spring.service;

import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.PageDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

/**
 * @author Klaus Lehner
 */
public interface UserAwareCrudlService<USER extends UserDetails,
        ID extends Serializable,
        LIST_DTO,
        DETAIL_DTO,
        SEARCH_REQUEST extends SearchRequest> {

    DETAIL_DTO create(USER currentUser, DETAIL_DTO fghData);

    void delete(USER currentUser, ID entityId);

    DETAIL_DTO get(USER currentUser, ID entityId);

    PageDto<LIST_DTO> list(SEARCH_REQUEST searchRequest);

    DETAIL_DTO update(USER currentUser, ID entityId, DETAIL_DTO fghData);
}
