package cc.catalysts.angular.demo.service;

import cc.catalysts.angular.demo.dto.CarModelDto;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.PageDto;
import cc.catalysts.angular.spring.service.CrudlService;

public interface CarModelService extends CrudlService<Long, CarModelDto, CarModelDto, SearchRequest> {

    PageDto<CarModelDto> findAllByManufaturerId(Long manufacturerId, SearchRequest searchRequest);

}
