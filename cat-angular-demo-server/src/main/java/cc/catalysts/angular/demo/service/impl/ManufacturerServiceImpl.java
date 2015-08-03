package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.ManufacturerDto;
import cc.catalysts.angular.demo.entity.Manufacturer;
import cc.catalysts.angular.demo.repository.ManufacturerRepository;
import cc.catalysts.angular.demo.service.ManufacturerService;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.service.AbstractCrudlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Mustafa on 03.08.2015.
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ManufacturerServiceImpl extends AbstractCrudlService<Long, Manufacturer, ManufacturerDto, ManufacturerDto, SearchRequest> implements ManufacturerService{
    @Autowired
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, ConversionService conversionService) {
        super(manufacturerRepository, conversionService);
    }

    @Override
    protected boolean canRead(Manufacturer manufacturer) {
        return true;
    }

    @Override
    protected boolean canUpdate(Manufacturer manufacturer) {
        return true;
    }

    @Override
    protected boolean canDelete(Manufacturer manufacturer) {
        return true;
    }

    @Override
    protected Manufacturer mergeFromDto(ManufacturerDto manufacturerDto, Manufacturer manufacturer) {
        manufacturer.setName(manufacturerDto.getName());

        return manufacturer;
    }
}
