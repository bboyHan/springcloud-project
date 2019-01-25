package com.bboyhan.search.repository;

import com.bboyhan.search.model.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/25 18:29
 */
@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article,String>{
}
