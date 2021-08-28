package com.example.leitorcomposto.reader;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.util.Assert;

public class CompositeJdbcPagingItemReader<T> extends JdbcPagingItemReader<T> {
  private PageProcessor<T> pageProcessor;

  public void setPageProcessor(PageProcessor<T> pageProcessor) {
    this.pageProcessor = pageProcessor;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    super.afterPropertiesSet();
    Assert.notNull(pageProcessor, "Page processor may not be null");
  }

  @Override
  protected void doReadPage() {
    super.doReadPage();
    if (!results.isEmpty()) {
      pageProcessor.process(results);
    }
  }
}