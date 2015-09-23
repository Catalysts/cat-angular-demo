package cc.catalysts.angular.spring.dto;

import java.util.Collection;

/**
 * The result DTO for a paged search request with the <code>totalCount</code>, the list of <code>elements</code> of the
 * requested page and the list of <code>facets</code> if requested.
 *
 * @param <T> The type of the element objects.
 * @author Klaus Lehner, Catalysts GmbH
 */
public class PageDto<T> {
    private Collection<T> elements;
    private long totalCount;

    /**
     * Necessary for JAXB unmarshalling.
     */
    PageDto() {
    }

    public PageDto(Collection<T> elements, long totalCount) {
        this.elements = elements;
        this.totalCount = totalCount;
    }

    public Collection<T> getElements() {
        return elements;
    }

    public void setElements(Collection<T> elements) {
        this.elements = elements;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
