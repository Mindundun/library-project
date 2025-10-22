package com.example.library_project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDto {
    
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;
    
}
