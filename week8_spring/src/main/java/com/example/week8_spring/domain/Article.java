package com.example.week8_spring.domain;

import com.example.week8_spring.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Article extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @OneToMany(mappedBy = "article")
    private List<Tag> tags;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    public Article(ArticleRequestDto articleRequestDto){
        this.title = articleRequestDto.getTitle();
        this.content=articleRequestDto.getContent();
    }

}
