package com.bboyhan.search.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @Auther: bboyHan
 * @Date: 2019/1/25 18:03
 */
@Document(indexName = "db_article",type = "tb_article_info")
@Data
public class Article {

    @Id
    private String id;
    private String remark;
    private String message;
    private Date createTime;
}
