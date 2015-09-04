package cc.catalysts.angular.demo.web.controller.api;

import cc.catalysts.angular.demo.dto.BookDto;
import cc.catalysts.angular.demo.service.BookService;
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
@RequestMapping("/api/books")
public class BookController  extends AbstractCrudlRestApi<BookDto, BookDto, SearchRequest> {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public BookDto create(@RequestBody BookDto dto) {
        return bookService.create(dto);
    }

    @Override
    public void delete(@PathVariable("id") Long id) {
        bookService.delete(id);
    }

    public BookDto get(@PathVariable("id") Long id) {
        return bookService.get(id);
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
    public PageDto<BookDto> list(SearchRequest searchRequest) {
        return bookService.list(searchRequest);
    }


    @Override
    public BookDto update(@PathVariable("id") Long id, @RequestBody BookDto dto) {
        return bookService.update(id, dto);
    }

}
