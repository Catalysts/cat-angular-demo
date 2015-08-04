package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.ManufacturerDto;
import cc.catalysts.angular.demo.entity.Manufacturer;
import cc.catalysts.angular.demo.repository.ManufacturerRepository;
import cc.catalysts.angular.demo.service.ManufacturerService;
import cc.catalysts.angular.demo.web.controller.api.ManufacturerSearchRequest;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.PageDto;
import cc.catalysts.angular.spring.service.AbstractCrudlService;
import cc.catalysts.angular.spring.util.SearchUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Mustafa on 03.08.2015.
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ManufacturerServiceImpl extends AbstractCrudlService<Long, Manufacturer, ManufacturerDto, ManufacturerDto, ManufacturerSearchRequest> implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, ConversionService conversionService) {
        super(manufacturerRepository, conversionService);
        this.manufacturerRepository = manufacturerRepository;
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

    @Override
    protected Page<Manufacturer> executeFindAll(ManufacturerSearchRequest searchRequest) {
        if (StringUtils.isNotEmpty(searchRequest.getName())) {
            return manufacturerRepository.findAllByNameLike("%" + searchRequest.getName() + "%", SearchUtils.toPageRequest(searchRequest));
        }

        return super.executeFindAll(searchRequest);
    }
}
