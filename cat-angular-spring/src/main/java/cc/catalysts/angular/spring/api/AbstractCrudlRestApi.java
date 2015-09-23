package cc.catalysts.angular.spring.api;

import cc.catalysts.angular.spring.core.Identifiable;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.NamedDto;
import cc.catalysts.angular.spring.dto.PageDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Base class for all rest controllers that define the basic CRUDL operations (create, read, update, delete, list).
 *
 * @param <TYPE>           The type of the DTO that is used when objects are showed in a list representation.
 *                         Normally only some properties of an object are necessary for a list.
 * @param <SEARCH_REQUEST> The type of the search request, e.g. just a simple paging or a more sophisticated search with
 *                         specific  search properties (=filter properties).
 * @author Thomas Scheinecker, Catalysts GmbH
 * @see CrudlApi
 */
public abstract class AbstractCrudlRestApi<TYPE extends Identifiable<Long>, LIST_TYPE extends Identifiable<Long>, SEARCH_REQUEST extends SearchRequest> implements CrudlApi<LIST_TYPE, TYPE, TYPE, SEARCH_REQUEST, Long> {
    // --------------------- Interface CrudlApi ---------------------

    /**
     * @param dto The DTO with all the properties of an object that should be created. The id of the DTO must be null to
     *            indicate that a new object should be created. Must not be null.
     * @return The detailed dto of the created object with the id that was generated.
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.POST)
    public abstract TYPE create(TYPE dto);

    /**
     * @param id The id of the object that should be deleted. Must not be null.
     */
    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public abstract void delete(Long id);

    /**
     * @param id The id of the object that should be retrieved. Must not be null.
     * @return The detailed DTO for the object with the given id or null of the object could not be found.
     */
    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public abstract TYPE get(Long id);

    /**
     * @param id The id of the object that should be retrieved. Must not be null.
     * @return The named DTO for the object with the given id or null of the object could not be found, i.e. same
     * as {@link #get(java.io.Serializable)}, but only the name and id are returned using the named dto.
     */
    @Override
    @RequestMapping(value = "/{id}", params = {"info"}, method = RequestMethod.GET)
    public abstract NamedDto<Long> info(Long id);

    /**
     * @param searchRequest All the filter properties for this list call including the paging information.
     *                      Must not be null.
     * @return The list of elements of the specified page filtered with the properties of the search request.
     */
    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    public abstract PageDto<LIST_TYPE> list(SEARCH_REQUEST searchRequest);

    /**
     * @param id  The id of the object that should be updated with the properties of the given DTO.
     * @param dto The DTO with all the properties of an object that should be updated. The id of the DTO must be the
     *            same as the given parameter id.
     * @return The detailed dto of the updated object (containing all the updates).
     */
    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public abstract TYPE update(Long id, TYPE dto);
}
