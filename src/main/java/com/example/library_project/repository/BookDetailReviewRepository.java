package com.example.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.library_project.entity.BookDetailReview;

public interface BookDetailReviewRepository extends JpaRepository<BookDetailReview, Long>{
    
}
