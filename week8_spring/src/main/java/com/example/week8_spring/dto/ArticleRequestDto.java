package com.example.week8_spring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequestDto {
    private Long idx;
    private String title;
    private String content;
    private String comment;
    private String tags;
}
