package cc.catalysts.angular.demo.web.controller.api;

import cc.catalysts.angular.demo.dto.BookDto;
import cc.catalysts.angular.spring.dto.PageDto;
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

    @RequestMapping
    public PageDto<BookDto> list() {
        List<BookDto> books = Arrays.asList(new BookDto());
        return new PageDto<BookDto>(books, books.size());
    }

}
