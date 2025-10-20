package com.example.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library_project.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    
}
