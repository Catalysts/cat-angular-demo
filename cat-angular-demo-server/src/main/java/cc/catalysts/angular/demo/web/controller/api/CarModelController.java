package cc.catalysts.angular.demo.web.controller.api;

import cc.catalysts.angular.demo.dto.CarModelDto;
import cc.catalysts.angular.demo.service.CarModelService;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manufacturers/{manufacturerId}/carmodels")
public class CarModelController {


    private final CarModelService carModelService;

    @Autowired
    public CarModelController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public CarModelDto create(@RequestBody CarModelDto dto) {
        return carModelService.create(dto);
    }
//
//    @Override
//    public void delete(@PathVariable("id") Long id) {
//        carModelService.delete(id);
//    }
//
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CarModelDto get(@PathVariable("id") Long id) {
        return carModelService.get(id);
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public PageDto<CarModelDto> list(@PathVariable("manufacturerId") Long manufacturerId, SearchRequest searchRequest) {
        return carModelService.findAllByManufaturerId(manufacturerId, searchRequest);
    }
//
//    @Override
//    public CarModelDto update(@PathVariable("id") Long id, @RequestBody CarModelDto dto) {
//        return carModelService.update(id, dto);
//    }

//    // TODO - delete?
//
//    @Override
//    public NamedDto<Long> info(@PathVariable("id") Long id) {
//        throw new NotImplementedException();
//    }
//
//    @Override
//    public PageDto<CarModelDto> list(SearchRequest searchRequest) {
//        return carModelService.list(searchRequest);
//    }
//
//    @Override
//    public List<NamedDto<Long>> infos(Iterable<Long> longs) {
//        throw new NotImplementedException();
//    }

}
