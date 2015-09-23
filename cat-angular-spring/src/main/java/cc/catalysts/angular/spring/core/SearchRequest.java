package cc.catalysts.angular.spring.core;


import java.util.Objects;

/**
 * It encapsulates all the sorting and paging information for a search request. It does not include any information
 * about the searched targets or the type of the search results<br>
 * The paging methodology splits a search result in to a number of pages with a fix size, the fist page is 0.
 * The user is able to iterate over this pages.
 * It is also possible to order the result by using the <code>sort</code> attribute. Allowed sort properties depend on
 * the target types to search.
 * <br>
 * This class is meant to be extended, all the search request classes must extends this class.
 *
 * @author Klaus Lehner (klaus.lehner@catalysts.cc)
 */
public class SearchRequest {
    /**
     * The default page size.
     */
    public static final int DEFAULT_ELEMENTS_IN_LIST = 100;
    /**
     * The maximum page size.
     */
    public static final int MAX_ELEMENTS_IN_LIST = 200;

    private int page;
    private int size = DEFAULT_ELEMENTS_IN_LIST;
    private String sort;

    public SearchRequest() {
    }

    public SearchRequest(int page, int size) {
        setPage(page);
        setSize(size);
    }

    public final void setSize(int size) {
        this.size = Math.min(MAX_ELEMENTS_IN_LIST, size);
    }

    public SearchRequest(int page, int size, String sort) {
        setPage(page);
        setSize(size);
        setSort(sort);
    }

    public int getPage() {
        return page;
    }

    public final void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public String getSort() {
        return sort;
    }

    public final void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SearchRequest other = (SearchRequest) obj;
        return Objects.equals(this.page, other.page)
                && Objects.equals(this.size, other.size)
                && Objects.equals(this.sort, other.sort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, size, sort);
    }
}
