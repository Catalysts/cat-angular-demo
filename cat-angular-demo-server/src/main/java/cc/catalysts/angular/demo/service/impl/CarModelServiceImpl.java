package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.CarModelDto;
import cc.catalysts.angular.demo.entity.CarModel;
import cc.catalysts.angular.demo.repository.CarModelRepository;
import cc.catalysts.angular.demo.service.CarModelService;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.service.AbstractCrudlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Throwable.class)
public class CarModelServiceImpl extends AbstractCrudlService<Long, CarModel, CarModelDto, CarModelDto, SearchRequest> implements CarModelService{
    @Autowired
    public CarModelServiceImpl(CarModelRepository carModelRepository, ConversionService conversionService) {
        super(carModelRepository, conversionService);
    }

    @Override
    protected boolean canRead(CarModel carModel) {
        return true;
    }

    @Override
    protected boolean canUpdate(CarModel carModel) {
        return true;
    }

    @Override
    protected boolean canDelete(CarModel carModel) {
        return true;
    }

    @Override
    protected CarModel mergeFromDto(CarModelDto carModelDto, CarModel carModel) {
        carModel.setName(carModelDto.getName());

        return carModel;
    }
}
