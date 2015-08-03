package cc.catalysts.angular.spring.dto;

import cc.catalysts.angular.spring.core.Named;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Basic DTO class with just an id and name.
 *
 * @author Klaus Lehner (klaus.lehner@catalysts.cc)
 */
public class NamedDto<ID extends Serializable> implements Named<ID> {
    private ID id;
    private String name;

    NamedDto() {
    }

    @Override
    public final ID getId() {
        return id;
    }

    @Override
    public final void setId(ID id) {
        this.id = id;
    }

    @Size(min = 0)
    @NotNull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
