package cc.catalysts.angular.demo.entity;

import cc.catalysts.angular.spring.entity.AbstractNamed;
import javax.persistence.Entity;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by Mustafa on 06.08.2015.
 */
@Entity
 public class Industry extends AbstractNamed<Long> {

    private Manufacturer manufacturer;

    @ManyToOne()
    @NotNull
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}