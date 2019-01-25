package com.bboyhan.search.controller;

import com.bboyhan.common.util.Result;
import com.bboyhan.search.model.Article;
import com.bboyhan.search.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/25 18:27
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 以新增为例，update \ delete \ find 可自行尝试
     */
    @PostMapping("/save")
    public Result saveArticle(@RequestBody Article article) {
        Article saveArticle = articleService.save(article);
        return Result.success(saveArticle);
    }
}
