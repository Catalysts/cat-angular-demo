package cc.catalysts.angular.demo.service;

import cc.catalysts.angular.demo.dto.VariationDto;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.PageDto;
import cc.catalysts.angular.spring.service.CrudlService;

/**
 * Created by Mustafa on 06.08.2015.
 */
public interface VariationService extends CrudlService<Long, VariationDto, VariationDto, SearchRequest> {

    PageDto<VariationDto> findAllByCarModelId(Long carModelId, SearchRequest searchRequest);

}