package cc.catalysts.angular.demo.dto;

import cc.catalysts.angular.spring.dto.NamedDto;

import javax.validation.constraints.NotNull;

/**
 * Created by Mustafa on 06.08.2015.
 */
public class IndustryDto extends NamedDto<Long> {

    private NamedDto<Long> manufacturer;

    @NotNull
    public NamedDto<Long> getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(NamedDto<Long> manufacturer) {
        this.manufacturer = manufacturer;
    }
}