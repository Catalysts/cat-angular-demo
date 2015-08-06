package cc.catalysts.angular.demo.web.controller.api;

import cc.catalysts.angular.demo.dto.IndustryDto;
import cc.catalysts.angular.demo.service.IndustryService;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/manufacturers/{manufacturerId}/industries")
public class IndustryController {

    private final IndustryService industryService;

    @Autowired
    public IndustryController(IndustryService industryService) {
        this.industryService = industryService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public IndustryDto create(@RequestBody IndustryDto dto) {
        return industryService.create(dto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public IndustryDto get(@PathVariable("id") Long id) {
        return industryService.get(id);
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public PageDto<IndustryDto> list(@PathVariable("manufacturerId") Long manufacturerId, SearchRequest searchRequest) {
        return industryService.findAllByManufaturerId(manufacturerId, searchRequest);
    }

}