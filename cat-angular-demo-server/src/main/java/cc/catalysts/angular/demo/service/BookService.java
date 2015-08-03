package cc.catalysts.angular.demo.service;


import cc.catalysts.angular.demo.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto get(Long id);
    List<BookDto> list();

}
