package cc.catalysts.angular.demo.web.controller.api;

import cc.catalysts.angular.demo.dto.VariationDto;
import cc.catalysts.angular.demo.service.VariationService;
import cc.catalysts.angular.spring.api.AbstractCrudlRestApi;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.NamedDto;
import cc.catalysts.angular.spring.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by thomas.scheinecker on 31.07.2015.
 */
@RestController
@RequestMapping("/api/manufacturers/{manufacturerId}/carmodels/{carmodelId}/variations")
public class VariationController extends AbstractCrudlRestApi<VariationDto, VariationDto, SearchRequest> {

    private final VariationService variationService;

    @Autowired
    public VariationController(VariationService variationService) {
        this.variationService = variationService;
    }

    @Override
    public VariationDto create(@RequestBody VariationDto dto) {
        return variationService.create(dto);
    }

    @Override
    public void delete(@PathVariable("id") Long id) {
        variationService.delete(id);
    }

    public VariationDto get(@PathVariable("id") Long id) {
        return variationService.get(id);
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
    public PageDto<VariationDto> list(SearchRequest searchRequest) {
        return variationService.list(searchRequest);
    }


    @Override
    public VariationDto update(@PathVariable("id") Long id, @RequestBody VariationDto dto) {
        return variationService.update(id, dto);
    }

}
