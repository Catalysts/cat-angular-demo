package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.BookDto;
import cc.catalysts.angular.demo.entity.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Mustafa on 03.08.2015.
 */
@Component
public class BookEntityConverter implements Converter<Book, BookDto> {
        @Override
        public BookDto convert(Book book) {
            BookDto bookDto = new BookDto();

            bookDto.setId(book.getId());
            bookDto.setName(book.getName());

            return bookDto;
        }
}
