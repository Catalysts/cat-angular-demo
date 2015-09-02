package cc.catalysts.angular.demo.service;


import cc.catalysts.angular.demo.dto.BookDto;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.service.CrudlService;

public interface BookService extends CrudlService<Long, BookDto, BookDto, SearchRequest> {


}
