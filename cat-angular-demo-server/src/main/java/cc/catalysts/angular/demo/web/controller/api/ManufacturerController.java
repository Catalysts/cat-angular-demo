package cc.catalysts.angular.demo.web.controller.api;

import cc.catalysts.angular.demo.dto.BookDto;
import cc.catalysts.angular.demo.dto.ManufacturerDto;
import cc.catalysts.angular.demo.service.ManufacturerService;
import cc.catalysts.angular.demo.service.impl.ManufacturerServiceImpl;
import cc.catalysts.angular.spring.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by thomas.scheinecker on 31.07.2015.
 */
@RestController
@RequestMapping("/api/manufacturer")
public class ManufacturerController {


    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @RequestMapping("/{id}")
    public ManufacturerDto get(@PathVariable("id") Long id) {
        return manufacturerService.get(id);
    }

    @RequestMapping
    public PageDto<ManufacturerDto> list() {
        List<ManufacturerDto> manufacturer = Arrays.asList(new ManufacturerDto());
        return new PageDto<ManufacturerDto>(manufacturer, manufacturer.size());
    }

}
