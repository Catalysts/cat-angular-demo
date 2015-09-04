package cc.catalysts.angular.demo.web.controller.api;

import cc.catalysts.angular.spring.core.SearchRequest;

/**
 * Created by Michael Mittermayr on 02.09.2015.
 */
public class CountrySearchRequest extends SearchRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
