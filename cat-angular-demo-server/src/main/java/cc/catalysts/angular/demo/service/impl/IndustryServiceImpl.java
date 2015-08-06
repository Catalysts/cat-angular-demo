package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.IndustryDto;
import cc.catalysts.angular.demo.entity.Industry;
import cc.catalysts.angular.demo.entity.Manufacturer;
import cc.catalysts.angular.demo.repository.IndustryRepository;
import cc.catalysts.angular.demo.repository.ManufacturerRepository;
import cc.catalysts.angular.demo.service.IndustryService;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.PageDto;
import cc.catalysts.angular.spring.service.AbstractCrudlService;
import cc.catalysts.angular.spring.util.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mustafa on 06.08.2015.
 */

@Service
@Transactional(rollbackFor = Throwable.class)
public class IndustryServiceImpl extends AbstractCrudlService<Long, Industry, IndustryDto, IndustryDto, SearchRequest> implements IndustryService {
    private final IndustryRepository industryRepository;
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public IndustryServiceImpl(IndustryRepository industryRepository, ManufacturerRepository manufacturerRepository, ConversionService conversionService) {
        super(industryRepository, conversionService);
        this.industryRepository = industryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    protected boolean canRead(Industry industry) {
        return true;
    }

    @Override
    protected boolean canUpdate(Industry industry) {
        return true;
    }

    @Override
    protected boolean canDelete(Industry industry) {
        return true;
    }

    @Override
    protected Industry mergeFromDto(IndustryDto industryDto, Industry industry) {
        industry.setName(industryDto.getName());

        Manufacturer manufacturer = manufacturerRepository.findOne(industryDto.getManufacturer().getId());
        industry.setManufacturer(manufacturer);

        return industry;
    }


    @Override
    public PageDto<IndustryDto> findAllByManufaturerId(Long manufacturerId, SearchRequest searchRequest) {
        Page<Industry> allByManufacturer = industryRepository.findAllByManufacturer(manufacturerRepository.getOne(manufacturerId), SearchUtils.toPageRequest(searchRequest));
        return convertList(allByManufacturer);
    }
}