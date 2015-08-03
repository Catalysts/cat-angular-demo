package cc.catalysts.angular.demo.service.impl;

import cc.catalysts.angular.demo.dto.BookDto;
import cc.catalysts.angular.demo.entity.Book;
import cc.catalysts.angular.demo.repository.BookRepository;
import cc.catalysts.angular.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(rollbackFor = Throwable.class)
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private static BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setName(book.getName());

        return dto;
    }

    @Override
    public BookDto get(Long id) {
        Book book = bookRepository.getOne(id);
        return toDto(book);
    }

    @Override
    public List<BookDto> list() {
        return bookRepository.findAll().stream().map(BookServiceImpl::toDto).collect(Collectors.toList());
    }

}
