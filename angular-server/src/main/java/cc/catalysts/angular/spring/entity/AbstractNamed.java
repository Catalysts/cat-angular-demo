package cc.catalysts.angular.spring.entity;

import cc.catalysts.angular.spring.core.Named;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by thomas.scheinecker on 03.08.2015.
 */
@MappedSuperclass
public abstract class AbstractNamed<ID extends Serializable>
        extends AbstractIdentifiable<ID>
        implements Named<ID> {

    private ID id;
    private String name;

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

    @Basic
    @NotNull
    @Size(min = 1)
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
