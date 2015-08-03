package cc.catalysts.angular.demo.entity;

import cc.catalysts.angular.spring.core.Identifiable;
import cc.catalysts.angular.spring.core.Named;
import cc.catalysts.angular.spring.entity.AbstractNamed;

import javax.annotation.Generated;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Mustafa on 03.08.2015.
 */
@Entity
public class Manufacturer extends AbstractNamed<Long> {
}
