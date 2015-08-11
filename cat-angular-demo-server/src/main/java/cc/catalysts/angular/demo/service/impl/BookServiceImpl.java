package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.BookDto;
import cc.catalysts.angular.demo.entity.Book;
import cc.catalysts.angular.demo.repository.BookRepository;
import cc.catalysts.angular.demo.service.BookService;
import cc.catalysts.angular.spring.core.SearchRequest;
import cc.catalysts.angular.spring.service.AbstractCrudlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Throwable.class)
public class BookServiceImpl  extends AbstractCrudlService<Long, Book, BookDto, BookDto, SearchRequest> implements BookService {

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ConversionService conversionService) {
        super(bookRepository, conversionService);
    }

    @Override
    protected boolean canRead(Book book) {
        return true;
    }

    @Override
    protected boolean canUpdate(Book book) {
        return true;
    }

    @Override
    protected boolean canDelete(Book book) {
        return true;
    }

    @Override
    protected Book mergeFromDto(BookDto bookDto, Book book) {
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());

        return book;
    }

}
