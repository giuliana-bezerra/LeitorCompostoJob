package com.example.leitorcomposto.reader;

import javax.sql.DataSource;

import com.example.leitorcomposto.domain.CustomerAccount;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
public class CompositeReaderConfig {
  @Bean
  public ItemReader<CustomerAccount> compositeReader(DataSource dataSource, PagingQueryProvider queryProvider) {
    CompositeJdbcPagingItemReader<CustomerAccount> reader = new CompositeJdbcPagingItemReader<>();
    reader.setDataSource(dataSource);
    reader.setPageSize(10);
    reader.setQueryProvider(queryProvider);
    reader.setRowMapper(new BeanPropertyRowMapper<>(CustomerAccount.class));
    CustomerAccountPageProcessor processor = new CustomerAccountPageProcessor();
    processor.setDataSource(dataSource);
    reader.setPageProcessor(processor);
    return reader;
  }

  @Bean
  public SqlPagingQueryProviderFactoryBean queryProvider(DataSource dataSource) {
    SqlPagingQueryProviderFactoryBean bean = new SqlPagingQueryProviderFactoryBean();
    bean.setDataSource(dataSource);
    bean.setSelectClause("SELECT *");
    bean.setFromClause("FROM customer_account");
    bean.setSortKey("id");
    return bean;
  }
}
