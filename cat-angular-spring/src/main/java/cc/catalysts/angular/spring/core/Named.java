package cc.catalysts.angular.spring.core;

import java.io.Serializable;

/**
 * Created by thomas.scheinecker on 03.08.2015.
 */
public interface Named<ID extends Serializable> extends Identifiable<ID> {

    String getName();

    void setName(String name);
}
