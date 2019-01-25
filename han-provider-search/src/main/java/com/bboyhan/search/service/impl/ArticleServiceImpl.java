package com.bboyhan.search.service.impl;

import com.bboyhan.search.model.Article;
import com.bboyhan.search.repository.ArticleRepository;
import com.bboyhan.search.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/25 19:26
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }
}
