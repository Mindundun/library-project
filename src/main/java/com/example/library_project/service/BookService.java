package com.example.library_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library_project.dto.BookDto;
import com.example.library_project.entity.Book;


@Service
public interface BookService {

    // 책 등록
    public Long createBook(BookDto bookDto);

    // 책 전체 조회
    public List<Book> getAllBooks();

    // 책 상세 조회
    public BookDto getBookById(Long id);

    // 책 수정
    public void putBook(BookDto bookDto);


    

    // Default Method
    // Entity to Dto
    default BookDto entityToDto(Book book) {

        return BookDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .author(book.getAuthor())
                        .description(book.getDescription())
                        .publisher(book.getPublisher())
                        .publishedDate(book.getPublishedDate())
                        .regDate(book.getRegDate())
                        .build();
    }

    // Dto to Entity
    default Book dtoToEntity(BookDto bookDto) {

        return Book.builder()
                    .id(bookDto.getId())
                    .name(bookDto.getName())
                    .author(bookDto.getAuthor())
                    .description(bookDto.getDescription())
                    .publisher(bookDto.getPublisher())
                    .publishedDate(bookDto.getPublishedDate())
                    .regDate(bookDto.getRegDate())
                    .build();
    }
}
