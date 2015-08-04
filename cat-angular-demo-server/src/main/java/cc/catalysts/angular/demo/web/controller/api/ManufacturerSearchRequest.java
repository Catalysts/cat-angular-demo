package cc.catalysts.angular.demo.web.controller.api;

import cc.catalysts.angular.spring.core.SearchRequest;

/**
 * Created by agerstmayr on 04.08.2015.
 */
public class ManufacturerSearchRequest extends SearchRequest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
