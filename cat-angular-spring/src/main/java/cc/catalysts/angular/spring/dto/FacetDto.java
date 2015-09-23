package cc.catalysts.angular.spring.dto;

import java.io.Serializable;
import java.util.List;

/**
 * A facet DTO is used for grouping information of data sets.
 * <p>
 * E.g. a certain object has a type and each object can have a different type: The FacetDTO is used to show the groups
 * of types, i.e. the <code>name</code> of the FacetDTO is the type, and the list of <code>facets</code> contain the
 * specific type with the number as count (how often they are set in the objects).
 * </p>
 *
 * @param <ID> The id type of the facet group term (typically Long or String for the persistent objects).
 * @author Klaus Lehner, Catalysts GmbH
 * @see FacetDto
 */
public class FacetDto<ID extends Serializable> {
    private String name;
    private List<FacetTermDto<ID>> facets;

    /**
     * @param name   The name of the facet.
     * @param facets The list of facets with name and count.
     */
    public FacetDto(String name, List<FacetTermDto<ID>> facets) {
        this.name = name;
        this.facets = facets;
    }

    /**
     * Necessary for JAXB unmarshalling.
     */
    FacetDto() {
    }

    /**
     * @return The list of facet groups specific for this name with the name within the group and count.
     */
    public List<FacetTermDto<ID>> getFacets() {
        return facets;
    }

    /**
     * @return The name of this facet group.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFacets(List<FacetTermDto<ID>> facets) {
        this.facets = facets;
    }
}
