package com.example.library_project.service;

import org.springframework.stereotype.Service;

import com.example.library_project.dto.BookDetailReviewDto;
import com.example.library_project.entity.Book;
import com.example.library_project.entity.BookDetailReview;
import com.example.library_project.repository.BookDetailReviewRepository;
import com.example.library_project.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookDetailReviewServiceImpl implements BookDetailReviewService{

    private final BookDetailReviewRepository bookDetailReviewRepository;
    private final BookRepository bookRepository;

    // 책 리뷰 등록
    @Override
    public Long createBookDetailReview(BookDetailReviewDto bookDetailReviewDto) {

        // Dto를 우선 Entity로 변환
        BookDetailReview review = dtoToEntity(bookDetailReviewDto);

        Book book = bookRepository.findById(bookDetailReviewDto.getBook().getId())
                                    .orElseThrow(() -> new RuntimeException("해당하는 " + bookDetailReviewDto.getBook().getId() + "의 책이 없습니다."));

        return bookDetailReviewRepository.save(review).getId();


    }
    
    // 수정
    // Long id = bookDetailReviewDto.getId();

    //     BookDetailReview bookDetailReview = bookDetailReviewRepository.findById(id)
    //                                             .orElseThrow(() -> new RuntimeException("해당하는 "+ id +"의 독후감이 없습니다."));

    //     bookDetailReview.setContent(bookDetailReviewDto.getContent());
        
    //     return bookDetailReviewRepository.save(dtoToEntity(bookDetailReviewDto)).getId();
    

    

}
