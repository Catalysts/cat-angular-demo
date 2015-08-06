package cc.catalysts.angular.demo.service;

import cc.catalysts.angular.demo.dto.IndustryDto;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.PageDto;
import cc.catalysts.angular.spring.service.CrudlService;

/**
 * Created by Mustafa on 06.08.2015.
 */

public interface IndustryService extends CrudlService<Long, IndustryDto, IndustryDto, SearchRequest> {

    PageDto<IndustryDto> findAllByManufaturerId(Long manufacturerId, SearchRequest searchRequest);

}
