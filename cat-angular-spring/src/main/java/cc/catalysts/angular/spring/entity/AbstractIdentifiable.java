package cc.catalysts.angular.spring.entity;

import cc.catalysts.angular.spring.core.Identifiable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by thomas.scheinecker on 03.08.2015.
 */
@MappedSuperclass
public abstract class AbstractIdentifiable<ID extends Serializable> implements Identifiable<ID> {

    private ID id;

    @Id
    @GeneratedValue
    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }
}
