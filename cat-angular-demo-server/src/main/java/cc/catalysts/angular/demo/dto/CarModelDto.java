package cc.catalysts.angular.demo.dto;

import cc.catalysts.angular.spring.dto.NamedDto;

import javax.validation.constraints.NotNull;

public class CarModelDto extends NamedDto<Long> {

    private NamedDto<Long> manufacturer;

    @NotNull
    public NamedDto<Long> getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(NamedDto<Long> manufacturer) {
        this.manufacturer = manufacturer;
    }
}
