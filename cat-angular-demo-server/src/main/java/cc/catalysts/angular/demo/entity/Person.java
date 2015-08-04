package cc.catalysts.angular.demo.entity;

import cc.catalysts.angular.spring.core.Identifiable;
import cc.catalysts.angular.spring.core.Named;
import cc.catalysts.angular.spring.entity.AbstractIdentifiable;
import cc.catalysts.angular.spring.entity.AbstractNamed;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Mustafa on 03.08.2015.
 */
@Entity
public class Person extends AbstractIdentifiable<Long> implements Named<Long> {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    @Override
    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException();
    }

    @Basic
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
