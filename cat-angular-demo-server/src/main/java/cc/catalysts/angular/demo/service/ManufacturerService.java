package cc.catalysts.angular.demo.service;

import cc.catalysts.angular.demo.dto.ManufacturerDto;
import cc.catalysts.angular.demo.web.controller.api.ManufacturerSearchRequest;
import cc.catalysts.angular.spring.service.CrudlService;

/**
 * Created by Mustafa on 03.08.2015.
 */
public interface ManufacturerService extends CrudlService<Long, ManufacturerDto, ManufacturerDto, ManufacturerSearchRequest> {

}
