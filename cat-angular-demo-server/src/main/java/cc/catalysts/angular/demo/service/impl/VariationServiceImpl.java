package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.VariationDto;
import cc.catalysts.angular.demo.entity.CarModel;
import cc.catalysts.angular.demo.entity.Variation;
import cc.catalysts.angular.demo.repository.CarModelRepository;
import cc.catalysts.angular.demo.repository.VariationRepository;
import cc.catalysts.angular.demo.service.VariationService;
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
public class VariationServiceImpl extends AbstractCrudlService<Long, Variation, VariationDto, VariationDto, SearchRequest> implements VariationService {
    private final VariationRepository variationRepository;
    private final CarModelRepository carModelRepository;

    @Autowired
    public VariationServiceImpl(VariationRepository variationRepository, CarModelRepository carModelRepository, ConversionService conversionService) {
        super(variationRepository, conversionService);
        this.variationRepository = variationRepository;
        this.carModelRepository = carModelRepository;
    }

    @Override
    protected boolean canRead(Variation variation) {
        return true;
    }

    @Override
    protected boolean canUpdate(Variation variation) {
        return true;
    }

    @Override
    protected boolean canDelete(Variation variation) {
        return true;
    }

    @Override
    protected Variation mergeFromDto(VariationDto variationDto, Variation variation) {
        variation.setName(variationDto.getName());

        CarModel carModel = carModelRepository.findOne(variationDto.getCarModel().getId());
        variation.setCarModel(carModel);

        return variation;
    }


    @Override
    public PageDto<VariationDto> findAllByCarModelId(Long carModelId, SearchRequest searchRequest) {
        Page<Variation> allByCarModels = variationRepository.findAllByCarModel(carModelRepository.getOne(carModelId), SearchUtils.toPageRequest(searchRequest));
        return convertList(allByCarModels);
    }
}