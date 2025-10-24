package com.example.library_project.service;

import org.springframework.stereotype.Service;

import com.example.library_project.dto.BookDetailReviewDto;
import com.example.library_project.entity.BookDetailReview;

@Service
public interface BookDetailReviewService {

    // 책 리뷰 등록
    public Long createBookDetailReview(BookDetailReviewDto bookDetailReviewDto);

    // 책 리뷰 상세 조회
    public BookDetailReviewDto getBookDetailReview(Long id);

    // Entity to DTO
    default BookDetailReviewDto entityToDto(BookDetailReview bookDetailReview) {
        return BookDetailReviewDto.builder()
                                    .id(bookDetailReview.getId())
                                    .content(bookDetailReview.getContent())
                                    .book(bookDetailReview.getBook())
                                    .regDate(bookDetailReview.getRegDate())
                                    .build();
    }

    default BookDetailReview dtoToEntity(BookDetailReviewDto bookDetailReviewDto){
        return BookDetailReview.builder()
                                .id(bookDetailReviewDto.getId())
                                .content(bookDetailReviewDto.getContent())
                                .regDate(bookDetailReviewDto.getRegDate())
                                .build();
    }
}
