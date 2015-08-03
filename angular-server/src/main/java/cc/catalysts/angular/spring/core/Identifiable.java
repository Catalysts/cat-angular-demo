package cc.catalysts.angular.spring.core;

import java.io.Serializable;

/**
 * Created by thomas.scheinecker on 03.08.2015.
 */
public interface Identifiable<ID extends Serializable> {

    ID getId();

    void setId(ID id);
}
