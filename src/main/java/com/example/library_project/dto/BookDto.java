package com.example.library_project.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.library_project.entity.BookDetailReview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
   
    private Long id;
    private String name;
    private String author;
    private String description;
    private String publisher;
    private LocalDate publishedDate;
    private LocalDateTime regDate;

    private BookDetailReviewDto review;
    
}
