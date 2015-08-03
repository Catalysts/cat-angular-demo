package cc.catalysts.angular.demo.web.controller.api;

import cc.catalysts.angular.demo.dto.BookDto;
import cc.catalysts.angular.demo.service.BookService;
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
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService manufacturerService) {
        this.bookService = manufacturerService;
    }

    @RequestMapping("/{id}")
    public BookDto get(@PathVariable("id") Long id) {
        return bookService.get(id);
    }

    @RequestMapping
    public PageDto<BookDto> list() {
        List<BookDto> books = bookService.list();
        return new PageDto<>(books, books.size());
    }

}
