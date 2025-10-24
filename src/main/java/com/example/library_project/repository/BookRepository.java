package com.example.library_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library_project.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    @Query("SELECT a FROM Book AS a LEFT OUTER JOIN FETCH a.review WHERE a.id = :id")
    Optional<Book> findByBookId(@Param("id") Long id);
}
