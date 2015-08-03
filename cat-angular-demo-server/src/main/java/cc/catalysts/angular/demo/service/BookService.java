package cc.catalysts.angular.demo.service;


import cc.catalysts.angular.demo.dto.BookDto;
import cc.catalysts.angular.demo.dto.ManufacturerDto;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.service.CrudlService;

import java.util.List;

public interface BookService extends CrudlService<Long, BookDto, BookDto, SearchRequest> {


}
