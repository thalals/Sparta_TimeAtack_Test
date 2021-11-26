package com.example.week8_spring.domain;


import com.example.week8_spring.dto.ArticleRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    @Column
    private String comment;

//    @JsonIgnore //숨기기
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Comment(ArticleRequestDto articleRequestDto, Article article){
        this.comment = articleRequestDto.getComment();
        this.article=article;
    }


}
