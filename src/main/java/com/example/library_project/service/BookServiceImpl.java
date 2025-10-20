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
    @Override
    @Transactional(readOnly = false)
    public Long createBook(BookDto bookDto) {
        // Dto를 Entity로 변경
        Book book = dtoToEntity(bookDto);
        bookRepository.save(book);
        return book.getId();
    }

    // 책 전체 조회
    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    // 책 상세 조회
    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다. ID : " + id));
        return entityToDto(book);
    }

    
    

}
