package com.example.week8_spring.service;

import com.example.week8_spring.domain.Article;
import com.example.week8_spring.dto.ArticleRequestDto;
import com.example.week8_spring.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(ArticleRequestDto articleRequestDto){
        Article article = new Article(articleRequestDto);
        articleRepository.save(article);
        return article;
    }

    public List<Article> getArticle(){
        return articleRepository.findAll();
    }
}
