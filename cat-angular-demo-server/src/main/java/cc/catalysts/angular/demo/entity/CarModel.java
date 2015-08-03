package cc.catalysts.angular.demo.entity;

import cc.catalysts.angular.spring.entity.AbstractNamed;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class CarModel extends AbstractNamed<Long> {

    private Manufacturer manufacturer;

    @ManyToOne
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
