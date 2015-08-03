package cc.catalysts.angular.spring.dto;

import java.io.Serializable;

/**
 * A facet term dto represents one entry in a facet group, i.e. a specific value (with <code>name</code> and
 * <code>id</code>) with the number of occurrences (<code>count</code>).
 *
 * @param <ID> The id type of the facet group term (typically Long or String for the persistent objects).
 * @author Klaus Lehner, Catalysts GmbH
 * @see FacetDto
 */
public class FacetTermDto<ID extends Serializable> {
    private ID id;
    private String name;
    private long count;

    /**
     * @param id    The persisted id of this specific facet term.
     * @param name  The specific value=name of this facet term.
     * @param count The number of occurrences of this specific value.
     */
    public FacetTermDto(ID id, String name, long count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    /**
     * Necessary for JAXB unmarshalling.
     */
    FacetTermDto() {
    }

    /**
     * @return The number of occurrences of this specific value.
     */
    public long getCount() {
        return count;
    }

    /**
     * @return The persisted id of this specific facet term.
     */
    public ID getId() {
        return id;
    }

    /**
     * @return The specific value=name of this facet term.
     */
    public String getName() {
        return name;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
