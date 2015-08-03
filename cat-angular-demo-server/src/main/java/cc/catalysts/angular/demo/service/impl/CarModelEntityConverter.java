package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.CarModelDto;
import cc.catalysts.angular.demo.entity.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CarModelEntityConverter implements Converter<CarModel, CarModelDto> {

    private ManufacturEntityConverter manufacturEntityConverter;

    @Autowired
    public CarModelEntityConverter(ManufacturEntityConverter manufacturEntityConverter) {
        this.manufacturEntityConverter = manufacturEntityConverter;
    }

    @Override
    public CarModelDto convert(CarModel carModel) {
        CarModelDto carModelDto = new CarModelDto();

        carModelDto.setId(carModel.getId());
        carModelDto.setName(carModel.getName());
        carModelDto.setManufacturer(manufacturEntityConverter.convert(carModel.getManufacturer()));

        return carModelDto;
    }
}
