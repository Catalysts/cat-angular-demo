package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.CarModelDto;
import cc.catalysts.angular.demo.entity.CarModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CarModelEntityConverter implements Converter<CarModel, CarModelDto> {
    @Override
    public CarModelDto convert(CarModel carModel) {
        CarModelDto carModelDto = new CarModelDto();

        carModelDto.setId(carModel.getId());
        carModelDto.setName(carModel.getName());

        return carModelDto;
    }
}
