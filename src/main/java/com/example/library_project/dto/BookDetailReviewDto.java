package com.example.library_project.dto;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDetailReviewDto {
    private Long id;
    private String content;
    private LocalDateTime regDate;
    private Long parantBookId;
}
