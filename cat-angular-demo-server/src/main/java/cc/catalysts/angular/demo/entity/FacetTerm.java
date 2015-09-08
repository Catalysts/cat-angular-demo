package cc.catalysts.angular.demo.entity;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

/**
 * Created by Michael Mittermayr on 07.09.2015.
 */
@SqlResultSetMapping(
        name = "FacetTermMapping",
        classes = {
                @ConstructorResult(targetClass = FacetTerm.class,
                        columns = {
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "value", type = Long.class)
                        }
                )
        }
)
public class FacetTerm {

    private String name;

    private long value;

    public FacetTerm(String name, long value) {
        this.name = name;
        this.value = value;
    }

    public FacetTerm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
