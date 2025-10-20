package com.example.library_project.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library_project.dto.BookDto;
import com.example.library_project.entity.Book;
import com.example.library_project.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 책 등록
    @PostMapping("/books")
    public ResponseEntity<Map<String, Long>> postBook(@RequestBody BookDto bookDto){

        bookDto.setRegDate(LocalDateTime.now());
        
        Long id = bookService.createBook(bookDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", id));
    }

    // 책 전체 조회
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();

        return ResponseEntity.ok().body(books);
    }

    // 책 상세 조회
    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable(name = "id") Long id) {

        return new String();
    }
    
    
    
}
