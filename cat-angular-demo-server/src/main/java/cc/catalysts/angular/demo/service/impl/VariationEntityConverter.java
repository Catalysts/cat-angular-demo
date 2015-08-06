package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.VariationDto;
import cc.catalysts.angular.demo.entity.Variation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Mustafa on 06.08.2015.
 */
@Component
public class VariationEntityConverter implements Converter<Variation, VariationDto> {

    private CarModelEntityConverter carModelEntityConverter;

    @Autowired
    public VariationEntityConverter(CarModelEntityConverter carModelEntityConverter) {
        this.carModelEntityConverter = carModelEntityConverter;
    }

    @Override
    public VariationDto convert(Variation variation) {
        VariationDto variationDto = new VariationDto();

        variationDto.setId(variation.getId());
        variationDto.setName(variation.getName());
        variationDto.setCarModel(carModelEntityConverter.convert(variation.getCarModel()));

        return variationDto;
    }
}
