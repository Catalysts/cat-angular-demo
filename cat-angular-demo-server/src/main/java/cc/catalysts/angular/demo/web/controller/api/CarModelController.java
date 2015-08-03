package cc.catalysts.angular.demo.web.controller.api;

import cc.catalysts.angular.demo.dto.CarModelDto;
import cc.catalysts.angular.demo.service.CarModelService;
import cc.catalysts.angular.spring.api.AbstractCrudlRestApi;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.NamedDto;
import cc.catalysts.angular.spring.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@RestController
@RequestMapping("/api/carmodels")
public class CarModelController extends AbstractCrudlRestApi<CarModelDto, CarModelDto, SearchRequest> {


    private final CarModelService carModelService;

    @Autowired
    public CarModelController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @Override
    public CarModelDto create(@RequestBody CarModelDto dto) {
        return carModelService.create(dto);
    }

    @Override
    public void delete(@PathVariable("id") Long id) {
        carModelService.delete(id);
    }

    public CarModelDto get(@PathVariable("id") Long id) {
        return carModelService.get(id);
    }

    @Override
    public PageDto<CarModelDto> list(SearchRequest searchRequest) {
        return carModelService.list(searchRequest);
    }

    @Override
    public CarModelDto update(@PathVariable("id") Long id, @RequestBody CarModelDto dto) {
        return carModelService.update(id, dto);
    }

    // TODO - delete?

    @Override
    public NamedDto<Long> info(@PathVariable("id") Long id) {
        throw new NotImplementedException();
    }

    @Override
    public List<NamedDto<Long>> infos(Iterable<Long> longs) {
        throw new NotImplementedException();
    }

}
