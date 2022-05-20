package com.lanrenspace.site.repository;

import com.lanrenspace.site.entity.Article;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @Author lanrenspace@163.com
 * @Description: 文章
 **/
public interface ArticleRepository extends ReactiveCrudRepository<Article, Long> {
}
