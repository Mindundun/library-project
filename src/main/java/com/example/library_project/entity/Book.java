package com.example.library_project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "review") // review 필드 제외
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private String description;

    private String publisher;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    // 일단 1:1이라 봄 
    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private BookDetailReview review;
  
    // 연관 관계 메소드
    public void setReview(BookDetailReview review){
        this.review = review;        
        review.setBook(this);
    }

    public void removeReview(BookDetailReview review){
        this.review.setBook(null);
        this.review = null;
        
    }

}
