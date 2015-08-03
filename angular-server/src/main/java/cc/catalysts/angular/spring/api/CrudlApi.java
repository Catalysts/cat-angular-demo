package cc.catalysts.angular.spring.api;


import cc.catalysts.angular.spring.core.Identifiable;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.NamedDto;
import cc.catalysts.angular.spring.dto.PageDto;

import java.io.Serializable;
import java.util.List;

/**
 * Base interface for all public APIs that define the basic CRUDL operations (create, read, update, list). This
 * interface is intended to be implemented and extended by the APIs for domain objects.
 *
 * @param <LIST>           The type of the DTO that is used when objects are showed in a list representation.
 *                         Normally only some properties of an object are necessary for a list.
 * @param <DETAIL>         The type of the DTO that is used when the object is shown in detail.
 * @param <EDIT>           The type of the DTO that is used when the object is modified, e.g. contains some additional
 *                         properties necessary to persist specific references.
 * @param <SEARCH_REQUEST> The type of the search request, e.g. just a simple paging or a more sophisticated search with
 *                         specific  search properties (=filter properties).
 * @param <ID>             The type of the id property of the object (typically Long or String).
 * @author Klaus Lehner, Catalysts GmbH
 * @author Harald Vogl, Catalysts GmbH
 */
public interface CrudlApi<LIST extends Identifiable<ID>,
        DETAIL extends Identifiable<ID>,
        EDIT extends Identifiable<ID>,
        SEARCH_REQUEST extends SearchRequest,
        ID extends Serializable> {
    /**
     * @param id The id of the object that should be deleted. Must not be null.
     */
    void delete(ID id);

    /**
     * @param id The id of the object that should be retrieved. Must not be null.
     * @return The detailed DTO for the object with the given id or null if the object could not be found.
     */
    DETAIL get(ID id);

    /**
     * @param id The id of the object that should be retrieved. Must not be null.
     * @return The named DTO for the object with the given id or null of the object could not be found
     */
    NamedDto<ID> info(ID id);

    /**
     * @param ids The list of ids of the objects that should be retrieved. Must not be null.
     * @return The list of named DTOs for the objects with the given ids.
     * @see CrudlApi#info
     */
    List<NamedDto<ID>> infos(Iterable<ID> ids);

    /**
     * @param searchRequest All the filter properties for this list call including the paging information.
     *                      Must not be null.
     * @return The list of elements of the specified page filtered with the properties of the search request.
     */
    PageDto<LIST> list(SEARCH_REQUEST searchRequest);

    /**
     * @param dto The DTO with all the properties of an object that should be created. Must not be null.
     * @return The detail DTO of the created object
     */
    DETAIL create(EDIT dto);

    /**
     * @param id  The id of the entity which should be updated. Must not be null.
     * @param dto The DTO with all the properties of an object that should be updated. Must not be null.
     * @return The detail DTO of the created object
     */
    DETAIL update(ID id, EDIT dto);
}
