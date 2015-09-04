package cc.catalysts.angular.demo.web.controller.api;

import cc.catalysts.angular.demo.dto.VariationDto;
import cc.catalysts.angular.demo.service.VariationService;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.NamedDto;
import cc.catalysts.angular.spring.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by thomas.scheinecker on 31.07.2015.
 */
@RestController
@RequestMapping("/api/manufacturers/{manufacturerId}/carmodels/{carmodelId}/variations")
public class VariationController {

    private final VariationService variationService;

    @Autowired
    public VariationController(VariationService variationService) {
        this.variationService = variationService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public VariationDto create(@RequestBody VariationDto dto) {
        return variationService.create(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        variationService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public VariationDto get(@PathVariable("id") Long id) {
        return variationService.get(id);
    }

    public NamedDto<Long> info(Long id) {
        return null;
    }

    public List<NamedDto<Long>> infos(Iterable<Long> longs) {
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public PageDto<VariationDto> list(@PathVariable("carmodelId") Long carmodelId, SearchRequest searchRequest) {
        return variationService.findAllByCarModelId(carmodelId, searchRequest);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public VariationDto update(@PathVariable("id") Long id, @RequestBody VariationDto dto) {
        return variationService.update(id, dto);
    }

}
