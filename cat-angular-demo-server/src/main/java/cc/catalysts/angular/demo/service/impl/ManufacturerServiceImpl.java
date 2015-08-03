package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.ManufacturerDto;
import cc.catalysts.angular.demo.entity.Manufacturer;
import cc.catalysts.angular.demo.repository.ManufacturerRepository;
import cc.catalysts.angular.demo.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Mustafa on 03.08.2015.
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ManufacturerServiceImpl implements ManufacturerService{
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public ManufacturerDto get(Long id) {
        Manufacturer manufacturer = manufacturerRepository.getOne(id);

        return new ManufacturerDto();
    }
}
