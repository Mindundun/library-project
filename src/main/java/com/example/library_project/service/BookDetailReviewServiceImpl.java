package com.example.library_project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library_project.dto.BookDetailReviewDto;
import com.example.library_project.entity.Book;
import com.example.library_project.entity.BookDetailReview;
import com.example.library_project.repository.BookDetailReviewRepository;
import com.example.library_project.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookDetailReviewServiceImpl implements BookDetailReviewService{

    private final BookDetailReviewRepository bookDetailReviewRepository;
    private final BookRepository bookRepository;

    // 책 리뷰 등록
    @Override
    @Transactional(readOnly = false)
    public Long createBookDetailReview(BookDetailReviewDto bookDetailReviewDto) {
        // Dto를 우선 Entity로 변환
        BookDetailReview review = dtoToEntity(bookDetailReviewDto);

        // 연관관계 매핑을 위한 조회
        Book book = bookRepository.findById(bookDetailReviewDto.getParantBookId())
                                    .orElseThrow(() -> new IllegalArgumentException("해당하는 " + bookDetailReviewDto.getParantBookId() + "의 책이 없습니다."));

        // 연관관계 매핑!(Book.java에 있는 연관관계 메소드)
        review.setBook(book); 

        return bookDetailReviewRepository.save(review).getId();
    }

    // 책 리뷰 조회
    @Override
    public BookDetailReviewDto getBookDetailReview(Long id) {
        BookDetailReview bookDetailReview = bookDetailReviewRepository.findById(id).get();

        return entityToDto(bookDetailReview);
    }

    @Override
    @Transactional(readOnly = false)
    public void modifyBookDetailReview(BookDetailReviewDto bookDetailReviewDto) {
        Long id = bookDetailReviewDto.getId();

        BookDetailReview bookDetailReview = bookDetailReviewRepository.findById(id)
                                                .orElseThrow(() -> new IllegalArgumentException("해당하는 "+ id +"의 독후감이 없습니다."));

        bookDetailReview.setContent(bookDetailReviewDto.getContent());
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteBookDetailReview(Long id) {
        bookDetailReviewRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당하는 "+ id +"의 독후감이 없습니다."));

        bookDetailReviewRepository.deleteById(id);        
    }

    
    
    

}
