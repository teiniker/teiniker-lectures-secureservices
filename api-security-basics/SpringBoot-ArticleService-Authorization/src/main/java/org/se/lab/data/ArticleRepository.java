package org.se.lab.data;

import org.se.lab.data.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long>
{
}

