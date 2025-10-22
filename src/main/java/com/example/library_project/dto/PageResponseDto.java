package com.example.library_project.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class PageResponseDto<T> {
    
    // 현재 페이지에 표시할 데이터 목록 (게시글 리스트 등)
    private List<T> dtoList;

    // 요청한 페이지 정보 (요청한 페이지 번호, 사이즈 등 담겨 있음)
    private PageRequestDto pageRequestDto;

    // 전체 데이터(게시글)의 수
    private int totalCount;

    // 현재 페이지 기준 이전과 다음이 존재하는지 여부
    private boolean prev = false, next = false;

    // 현재 페이지 블록의 시작 페이지 번호 (예: 11~20이면 11, 20)
    private int start = 0, end = 0;

    private int prevPage = 0, nextPage = 0, totalPage = 0, currentPage = 0, size = 0;

    // 페이지 블록 수
    private int pageSize = 10; 

    // 페이지 번호, 현재 블록에 표시할 페이지 번호 목록 (예: [11,12,...,20])
    private List<Integer> pageNumList = new ArrayList<>();

    @Builder
    public PageResponseDto(List<T> dtoList, PageRequestDto pageRequestDto, int totalCount) {
        this.dtoList = dtoList;
        this.pageRequestDto = pageRequestDto;
        this.totalCount = totalCount;

        this.currentPage = pageRequestDto.getPage();

        this.size = pageRequestDto.getSize();

        // 현재 페이지 번호가 속한 페이지 블록의 마지막 페이지 번호 계산
        end = (int)(Math.ceil(currentPage / (double)pageSize)) * pageSize;

        log.info("현재 페이지, current Page : {} ", currentPage);
        log.info("페이지 블록 수, pageSize : {} ", pageSize);
        log.info("현재 블록 목록 중 가장 마지막 페이지, end : {} ", end);

        // 현재 페이지 번호가 속한 페이지 블록의 첫번째 페이지 번호 계산
        // 만약 end가 20, pageSize는 10인 경우 start는 [20 - (10 - 1)]로 11가 된다
        start = end - (pageSize - 1);
        log.info("현재 블록 목록 중 첫번쨰 마지막 페이지, start : {} ", start);

        // 총 페이지 수를 계산
        // 총 글의 수가 133이면 size인 10을 나눠 13.3이 되는데 올림하기에 14!
        int lastPage = (int)Math.ceil(totalCount / (double)size);
        
        log.info("총 페이지 수, lastPage : {} ", lastPage);

        this.totalPage = lastPage;

        log.info("총 페이지 수, totalPage : {} ", totalPage);

        // 끝 페이지 번호가 총 페이지 수를 넘지 않도록
        end = end > lastPage ? lastPage : end;

        log.info("현재 블록 목록 중 가장 마지막 페이지와 총 페이지 비교, end2 : {} ", end);

        // 이전/다음 버튼 활성화 여부
        // 1페이지보다 크면 이전 버튼 활성화
        this.prev = start > 1;
        // 총 페이지수가 페이지 블록 내 마지막 * 10보다 크면 다음 버튼 활성화
        // 133 > (10 *10) => 다은 버튼 활성화  → 이 블록에서 커버 가능한 최대 게시글 수!
        this.next = totalCount > (end * size);

        log.info("이전 페이지 여부, prev : {} ", prev);
        log.info("다음 페이지 여부, next : {} ", next);

        // 페이지 번호 생성(밑에 페이지 리스트 버튼!)
        // IntStream -> Stream<Integer> -> List<Integer>
        this.pageNumList = IntStream.range(start, end + 1).boxed().collect(Collectors.toList());
        
        log.info("pageNumList : {}", pageNumList);

        // 이전(prev) 활성화된 경우 prevPage는 이전 페이지 블록의 마지막 페이지 번호로 이동
        // 이전(prev) 비활성화된 경우 prevPage는 0으로 설정
        this.prevPage = prev ? start - 1 : 0;

        log.info("prevPage : {}", prevPage);

        // 다음(next) 활성화된 경우 nextPage는 다음 페이지 블록의 첫 페이지 번호로 이동
        // 다음(next) 비활성화된 경우 nextPage는 0으로 설정
        this.nextPage = next ? end + 1 : 0;

        log.info("nextPage : {}", nextPage);

    }
}
