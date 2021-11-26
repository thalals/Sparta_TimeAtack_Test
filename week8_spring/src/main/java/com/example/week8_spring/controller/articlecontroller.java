package com.example.week8_spring.controller;

import com.example.week8_spring.domain.Article;
import com.example.week8_spring.dto.ArticleRequestDto;
import com.example.week8_spring.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class articlecontroller {

    private final ArticleService articleService;

    @PostMapping("/article")
    public Article postarticle(@RequestBody ArticleRequestDto articlerequestdto){
        Article article = articleService.createArticle(articlerequestdto);

        return article;
    }

    @GetMapping("/article")
    public List<Article> getArtile(){
        return articleService.getArticle();
    }

    @GetMapping("/article/{id}")
    public Article getArtiles(@PathVariable Long id){
        System.out.println(id);
        return articleService.getArticles(id);
    }

    @PostMapping("/article/comment")
    public void setComment(@RequestBody ArticleRequestDto articlerequestdto){
        System.out.println(articlerequestdto.getComment());
        System.out.println(articlerequestdto.getIdx());
        articleService.setArticleComment(articlerequestdto);
    }


}
