package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.CountryDto;
import cc.catalysts.angular.demo.entity.Country;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Michael Mittermayr on 02.09.2015.
 */
@Component
public class CountryEntityConverter implements Converter<Country, CountryDto> {


    @Override
    public CountryDto convert(Country country) {
        CountryDto countryDto = new CountryDto(country.getName(), country.getCode(), country.isDisabled());

        countryDto.setId(country.getId());

        return countryDto;
    }
}
