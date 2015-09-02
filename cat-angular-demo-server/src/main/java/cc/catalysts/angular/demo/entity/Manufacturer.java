package cc.catalysts.angular.demo.entity;

import cc.catalysts.angular.spring.entity.AbstractNamed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Mustafa on 03.08.2015.
 */
@Entity
public class Manufacturer extends AbstractNamed<Long> {

    private LocalDate foundingDate;

    private Country country;

    private List<Person> owners;
    private List<CarModel> carModels;

    @Basic
    @NotNull
    @cc.catalysts.angular.demo.validator.Past
    public LocalDate getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(LocalDate foundingDate) {
        this.foundingDate = foundingDate;
    }

    @OneToMany(cascade = CascadeType.DETACH, orphanRemoval = true)
    public List<Person> getOwners() {
        return owners;
    }

    public void setOwners(List<Person> owners) {
        this.owners = owners;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CarModel> getCarModels() {
        return carModels;
    }


    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }

    @ManyToOne()
    @NotNull
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
