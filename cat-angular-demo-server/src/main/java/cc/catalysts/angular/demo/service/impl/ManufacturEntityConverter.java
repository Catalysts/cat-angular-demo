package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.ManufacturerDto;
import cc.catalysts.angular.demo.entity.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Mustafa on 03.08.2015.
 */
@Component
public class ManufacturEntityConverter implements Converter<Manufacturer, ManufacturerDto> {

    private CountryEntityConverter countryEntityConverter;

    @Autowired
    public ManufacturEntityConverter(CountryEntityConverter countryEntityConverter) {
        this.countryEntityConverter = countryEntityConverter;
    }

    @Override
    public ManufacturerDto convert(Manufacturer manufacturer) {
        ManufacturerDto manufacturerDto = new ManufacturerDto();

        manufacturerDto.setId(manufacturer.getId());
        manufacturerDto.setName(manufacturer.getName());
        manufacturerDto.setCountry(countryEntityConverter.convert(manufacturer.getCountry()));
        manufacturerDto.setFoundingDate(manufacturer.getFoundingDate());

        return manufacturerDto;
    }
}
