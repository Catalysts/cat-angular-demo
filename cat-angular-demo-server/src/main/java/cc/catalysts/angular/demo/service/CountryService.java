package cc.catalysts.angular.demo.service;

import cc.catalysts.angular.demo.dto.CountryDto;
import cc.catalysts.angular.demo.web.controller.api.CountrySearchRequest;
import cc.catalysts.angular.spring.service.CrudlService;

/**
 * Created by Michael Mittermayr on 02.09.2015.
 */
public interface CountryService extends CrudlService<Long, CountryDto, CountryDto, CountrySearchRequest> {
}
