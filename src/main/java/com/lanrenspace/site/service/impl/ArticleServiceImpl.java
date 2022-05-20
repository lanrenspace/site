package com.lanrenspace.site.service.impl;

import com.lanrenspace.site.base.BaseBeanServiceImpl;
import com.lanrenspace.site.entity.Article;
import com.lanrenspace.site.repository.ArticleRepository;
import com.lanrenspace.site.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * @Author lanrenspace@163.com
 * @Description:
 **/
@Service
public class ArticleServiceImpl extends BaseBeanServiceImpl<Article, Long, ArticleRepository> implements ArticleService {
}
