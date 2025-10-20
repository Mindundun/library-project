package com.example.library_project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library_project.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    // 책 등록하기
    @Transactional(readOnly = false)
    public Long postBook(BookDto bookDto) {
        // Dto를 Entity로 변경
        bookRepository.save(b)
        return 1L;
    }

}
