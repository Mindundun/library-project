package com.example.library_project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.library_project.dto.BookDto;
import com.example.library_project.dto.PageRequestDto;
import com.example.library_project.dto.PageResponseDto;
import com.example.library_project.entity.Book;
import com.example.library_project.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    // 책 등록하기
    @Override
    @Transactional(readOnly = false)
    public Long createBook(BookDto bookDto) {
        // Dto를 Entity로 변경
        Book book = dtoToEntity(bookDto);
        bookRepository.save(book);
        return book.getId();
    }

    // 책 전체 조회
    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    // 페이징 처리
    @Override
    public PageResponseDto<BookDto> paging(PageRequestDto pageRequestDto) {
        
        // 페이징 처리를 위해 (페이지번호, 사이즈, 정렬조건) 설정
        Pageable pageable = PageRequest.of(pageRequestDto.getPage()-1, pageRequestDto.getSize(), Sort.by("id").descending());

        // 페이징 처리한 기준으로 데이터를 가져와 담음
        Page<Book> page = bookRepository.findAll(pageable);

        // 담은 데이터를 변환
        List<BookDto> posts = page.getContent().stream().map(book -> entityToDto(book)).collect(Collectors.toList());

        // 전체 게시글 수
        int totalCount = (int)page.getTotalElements();

        return PageResponseDto.<BookDto>builder()
                                .dtoList(posts)
                                .pageRequestDto(pageRequestDto)
                                .totalCount(totalCount)
                                .build();
    }

    // 책 상세 조회
    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다. ID : " + id));
        return entityToDto(book);
    }

    

    // 책 수정
    @Override
    @Transactional(readOnly = false)
    public void putBook(BookDto bookDto) {
        Long id = bookDto.getId();

        Book book = bookRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("해당하는 "+id+"의 책이 없습니다."));

        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        book.setPublisher(bookDto.getPublisher());
        book.setPublishedDate(bookDto.getPublishedDate());
    }

    // 책 삭제
    @Override
    @Transactional(readOnly = false)
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);        
    }

    

    
}
