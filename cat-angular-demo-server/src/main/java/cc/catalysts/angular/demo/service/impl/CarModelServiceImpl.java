package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.CarModelDto;
import cc.catalysts.angular.demo.entity.CarModel;
import cc.catalysts.angular.demo.entity.Manufacturer;
import cc.catalysts.angular.demo.repository.CarModelRepository;
import cc.catalysts.angular.demo.repository.ManufacturerRepository;
import cc.catalysts.angular.demo.service.CarModelService;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.PageDto;
import cc.catalysts.angular.spring.service.AbstractCrudlService;
import cc.catalysts.angular.spring.util.SearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Throwable.class)
public class CarModelServiceImpl extends AbstractCrudlService<Long, CarModel, CarModelDto, CarModelDto, SearchRequest> implements CarModelService{
    private final CarModelRepository carModelRepository;
    private final ManufacturerRepository manufacturerRepository;

    @Autowired
    public CarModelServiceImpl(CarModelRepository carModelRepository, ManufacturerRepository manufacturerRepository, ConversionService conversionService) {
        super(carModelRepository, conversionService);
        this.carModelRepository = carModelRepository;
        this.manufacturerRepository = manufacturerRepository;
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

        Manufacturer manufacturer = manufacturerRepository.findOne(carModelDto.getManufacturer().getId());
        carModel.setManufacturer(manufacturer);

        return carModel;
    }


    @Override
    public PageDto<CarModelDto> findAllByManufaturerId(Long manufacturerId, SearchRequest searchRequest) {
        Page<CarModel> allByManufacturer = carModelRepository.findAllByManufacturer(manufacturerRepository.getOne(manufacturerId), SearchUtils.toPageRequest(searchRequest));
        return convertList(allByManufacturer);
    }
}
