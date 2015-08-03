package cc.catalysts.angular.demo.service;

import cc.catalysts.angular.demo.dto.ManufacturerDto;
import cc.catalysts.angular.demo.entity.Manufacturer;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.service.AbstractCrudlService;
import cc.catalysts.angular.spring.service.CrudlService;

import java.util.List;

/**
 * Created by Mustafa on 03.08.2015.
 */
public interface ManufacturerService extends CrudlService<Long, ManufacturerDto, ManufacturerDto, SearchRequest> {

}
