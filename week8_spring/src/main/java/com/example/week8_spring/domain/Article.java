package com.example.week8_spring.domain;

import com.example.week8_spring.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public Article(ArticleRequestDto articleRequestDto){
        this.title = articleRequestDto.getTitle();
        this.content=articleRequestDto.getContent();
    }

}
