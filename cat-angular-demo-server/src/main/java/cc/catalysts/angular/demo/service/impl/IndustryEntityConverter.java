package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.IndustryDto;
import cc.catalysts.angular.demo.entity.Industry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Mustafa on 06.08.2015.
 */
@Component
public class IndustryEntityConverter implements Converter<Industry, IndustryDto> {

    private ManufacturEntityConverter manufacturEntityConverter;

    @Autowired
    public IndustryEntityConverter(ManufacturEntityConverter manufacturEntityConverter) {
        this.manufacturEntityConverter = manufacturEntityConverter;
    }

    @Override
    public IndustryDto convert(Industry industry) {
        IndustryDto industryDto = new IndustryDto();

        industryDto.setId(industry.getId());
        industryDto.setName(industry.getName());
        industryDto.setManufacturer(manufacturEntityConverter.convert(industry.getManufacturer()));

        return industryDto;
    }
}