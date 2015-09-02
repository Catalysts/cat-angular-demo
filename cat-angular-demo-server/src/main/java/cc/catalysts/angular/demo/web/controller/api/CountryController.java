package cc.catalysts.angular.demo.web.controller.api;

import cc.catalysts.angular.demo.dto.CountryDto;
import cc.catalysts.angular.demo.service.CountryService;
import cc.catalysts.angular.spring.api.AbstractCrudlRestApi;
import cc.catalysts.angular.spring.dto.NamedDto;
import cc.catalysts.angular.spring.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Michael Mittermayr on 01.09.2015.
 */
@RestController
@RequestMapping("/api/countries")
public class CountryController extends AbstractCrudlRestApi<CountryDto, CountryDto, CountrySearchRequest> {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public CountryDto create(CountryDto dto) {
        return countryService.create(dto);
    }

    @Override
    public void delete(Long id) {
        countryService.delete(id);
    }

    @Override
    public CountryDto get(Long id) {
        return countryService.get(id);
    }

    @Override
    public NamedDto<Long> info(Long id) {
        return null;
    }

    @Override
    public List<NamedDto<Long>> infos(Iterable<Long> longs) {
        return null;
    }

    @Override
    public PageDto<CountryDto> list(CountrySearchRequest searchRequest) {
        return countryService.list(searchRequest);
    }

    @Override
    public CountryDto update(Long id, CountryDto dto) {
        return countryService.update(id, dto);
    }
}
