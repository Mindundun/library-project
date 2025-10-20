package com.example.library_project.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookDto {
   
    private Long id;
    private String name;
    private String author;
    private String description;
    private String publisher;
    private LocalDate publishedDate;
    private LocalDateTime regDate;

}
