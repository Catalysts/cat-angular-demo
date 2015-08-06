package cc.catalysts.angular.demo.dto;

import cc.catalysts.angular.spring.dto.NamedDto;

import javax.validation.constraints.NotNull;

/**
 * Created by Mustafa on 06.08.2015.
 */

public class VariationDto extends NamedDto<Long> {

    private NamedDto<Long> carModel;

    @NotNull
    public NamedDto<Long> getCarModel() {
        return carModel;
    }

    public void setCarModel(NamedDto<Long> carModel) {
        this.carModel = carModel;
    }
}