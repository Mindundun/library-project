package com.example.library_project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.library_project.entity.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void testPageDefault() {

        // 1페이지 10개씩
        Pageable pageable = PageRequest.of(0, 10); 
        // 아래의 구문 실행 시 limit 포함 SELECT 절 1개, 전체 count 수 SELECT 절 1개
        Page<Book> result = bookRepository.findAll(pageable);

        // 총 몇 페이지 : 2
        log.info("============Total Page : {}", result.getTotalPages());

        // 전체 개수 : 13
        log.info("============Total Elements : {}", result.getTotalElements());

        // 현재 페이지 번호. 0부터 시작 : 0
        log.info("============now Page : {}", result.getNumber());

        // 페이지당 데이터 개수 : 13
        log.info("============Page in Page : {}", result.getSize());

        // 다음 페이지 존재 여부 : true
        log.info("============has next Page? : {}", result.hasNext());

        // 시작 페이지 존재 여부 : false
        log.info("============firest Page? : {}", result.hasPrevious());
    }

    @Test
    void testPageSort() {

        Sort sort = Sort.by("name").descending();

        // 1페이지 10개씩
        Pageable pageable = PageRequest.of(0, 10, sort); 
        // 아래의 구문 실행 시 limit 포함 SELECT 절 1개, 전체 count 수 SELECT 절 1개
        Page<Book> result = bookRepository.findAll(pageable);

        // 총 몇 페이지 : 2
        log.info("============Total Page : {}", result.getTotalPages());

        // 전체 개수 : 13
        log.info("============Total Elements : {}", result.getTotalElements());

        // 현재 페이지 번호. 0부터 시작 : 0
        log.info("============now Page : {}", result.getNumber());

        // 페이지당 데이터 개수 : 13
        log.info("============Page in Page : {}", result.getSize());

        // 다음 페이지 존재 여부 : true
        log.info("============has next Page? : {}", result.hasNext());

        // 시작 페이지 존재 여부 : false
        log.info("============firest Page? : {}", result.hasPrevious());

        result.get().forEach(book ->{
            log.info("book : {}", book.getName());
        });
    }

    @Test
    void testPageMutiSort() {

        Sort sort1 = Sort.by("name").descending();
        Sort sort2 = Sort.by("id").ascending();
        Sort sortAll = sort1.and(sort2);

        // 1페이지 10개씩
        Pageable pageable = PageRequest.of(0, 10, sortAll); 
        // 아래의 구문 실행 시 limit 포함 SELECT 절 1개, 전체 count 수 SELECT 절 1개
        Page<Book> result = bookRepository.findAll(pageable);

        // 총 몇 페이지 : 2
        log.info("============Total Page : {}", result.getTotalPages());

        // 전체 개수 : 13
        log.info("============Total Elements : {}", result.getTotalElements());

        // 현재 페이지 번호. 0부터 시작 : 0
        log.info("============now Page : {}", result.getNumber());

        // 페이지당 데이터 개수 : 13
        log.info("============Page in Page : {}", result.getSize());

        // 다음 페이지 존재 여부 : true
        log.info("============has next Page? : {}", result.hasNext());

        // 시작 페이지 존재 여부 : false
        log.info("============firest Page? : {}", result.hasPrevious());

        result.get().forEach(book ->{
            log.info("book : {}", book.getName());
        });
    }
}
