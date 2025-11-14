package com.example.library_project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library_project.dto.BookDetailReviewDto;
import com.example.library_project.service.BookDetailReviewService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class BookDetailReviewController {

    private final BookDetailReviewService bookDetailReviewService;
    
    // 책 리뷰 등록
    @PostMapping("/bookDetailReviews")
    public ResponseEntity<Map<String, Long>> postMethodName(@RequestBody BookDetailReviewDto bookDetailReviewDto) {
        // 등록 일시 세팅
        bookDetailReviewDto.setRegDate(LocalDateTime.now());

        Long id = bookDetailReviewService.createBookDetailReview(bookDetailReviewDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id",id));
        
    }

    // 책 리뷰 조회
    @GetMapping("/bookDetailReviews/{id}")
    public ResponseEntity<BookDetailReviewDto> getBookDetailReview(@PathVariable(value = "id") Long id) {
        BookDetailReviewDto bookDetailReviewDto = bookDetailReviewService.getBookDetailReview(id);

        return ResponseEntity.status(HttpStatus.OK).body(bookDetailReviewDto);
    }
    
    // 책 리뷰 수정
    @PutMapping("bookDetailReviews/{id}")
    public ResponseEntity<Map<String,String>> updateBookDetailReview(@PathVariable(value = "id") Long id, @RequestBody BookDetailReviewDto bookDetailReviewDto){
        bookDetailReviewDto.setId(id);

        bookDetailReviewService.modifyBookDetailReview(bookDetailReviewDto);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","수정완료"));
    }

    // 책 리뷰 삭제
    @DeleteMapping("bookDetailReviews/{id}")
    public ResponseEntity<Map<String, String>> deleteBookDetailReview(@PathVariable(value = "id") Long id){
        bookDetailReviewService.deleteBookDetailReview(id);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "삭제완료"));
    }
    
}
