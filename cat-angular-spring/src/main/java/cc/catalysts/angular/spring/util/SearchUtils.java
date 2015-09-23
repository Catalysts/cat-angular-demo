package cc.catalysts.angular.spring.util;

import cc.catalysts.angular.spring.core.SearchRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Klaus Lehner, Catalysts GmbH
 */
public final class SearchUtils {

    private static final String NON_INDEX_CHARS = "\\%|_|";

    private SearchUtils() {
    }

    public static PageRequest toPageRequest(SearchRequest request) {
        return toPageRequest(request, null);
    }

    public static PageRequest toPageRequest(SearchRequest request, Sort defaultSort) {
        if (request == null) {
            throw new IllegalArgumentException("request must not be null");
        }
        if (StringUtils.hasText(request.getSort())) {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();

            for (String s : request.getSort().split(";")) {
                if (s.contains(":")) {
                    String[] split = s.split(":");
                    orders.add(new Sort.Order(Sort.Direction.fromString(split[1]), split[0]));
                } else {
                    orders.add(new Sort.Order(s));
                }
            }
            return new PageRequest(request.getPage(), request.getSize(), new Sort(orders));
        } else {
            return new PageRequest(request.getPage(), request.getSize(), defaultSort);
        }
    }

    public static String escape(String query, boolean indexSearch) {
        return query != null ? query.replaceAll("(" + (indexSearch ? "" : NON_INDEX_CHARS) + "\\+|-|&|\\||!|\\(|\\)"
                + "|\\{|\\}|\\[|\\]|\\^|\"|~|\\*|\\?|:|\\\\|/)", indexSearch ? " " : "\\\\$1") : null;
    }

    public static String escape(String query) {
        return escape(query, false);
    }
}
