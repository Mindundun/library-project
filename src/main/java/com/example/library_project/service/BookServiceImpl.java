package com.example.library_project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library_project.dto.BookDto;
import com.example.library_project.entity.Book;
import com.example.library_project.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    // 책 등록하기
    @Transactional(readOnly = false)
    public Long createBook(BookDto bookDto) {
        // Dto를 Entity로 변경
        Book book = dtoToEntity(bookDto);
        bookRepository.save(book);
        return book.getId();
    }

    // 책 전체 조회
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    

}
