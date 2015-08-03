package cc.catalysts.angular.spring.dto;

import java.io.Serializable;
import java.util.Collection;

/**
 * The result DTO for a paged search request with the <code>totalCount</code>, the list of <code>elements</code> of the
 * requested page and the list of <code>facets</code> if requested.
 *
 * @param <T> The type of the element objects.
 * @author Klaus Lehner, Catalysts GmbH
 */
public class PageWithFacetDto<T, ID extends Serializable> extends PageDto<T> {
    private Collection<FacetDto<ID>> facets;

    /**
     * Necessary for JAXB unmarshalling.
     */
    PageWithFacetDto() {
    }


    public PageWithFacetDto(Collection<T> elements, long totalCount, Collection<FacetDto<ID>> facets) {
        super(elements, totalCount);
        this.facets = facets;
    }

    public Collection<FacetDto<ID>> getFacets() {
        return facets;
    }

    public void setFacets(Collection<FacetDto<ID>> facets) {
        this.facets = facets;
    }

    /**
     * Convenience method.
     *
     * @param name The name of a facet.
     * @return The facet dto with the given name or null if a facet for this name is not available.
     */
    public FacetDto getFacet(String name) {
        if (facets != null) {
            for (FacetDto facet : facets) {
                if (facet.getName().equals(name)) {
                    return facet;
                }
            }
        }
        return null;
    }
}
