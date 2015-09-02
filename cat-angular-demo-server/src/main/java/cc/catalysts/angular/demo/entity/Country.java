package cc.catalysts.angular.demo.entity;

import cc.catalysts.angular.spring.entity.AbstractNamed;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Michael Mittermayr on 02.09.2015.
 */
@Entity
public class Country extends AbstractNamed<Long> {

    // used to filter at client side...just to show how to use a filter function with cat-select
    private boolean disabled;

    @Column(unique = true)
    private String code;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
