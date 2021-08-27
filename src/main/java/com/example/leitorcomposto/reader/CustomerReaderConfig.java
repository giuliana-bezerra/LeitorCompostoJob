package com.example.leitorcomposto.reader;

import javax.sql.DataSource;

import com.example.leitorcomposto.domain.Customer;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerReaderConfig {
  @Bean
  public ItemReader<Customer> customerReader(DataSource dataSource) {
    return new JdbcCursorItemReaderBuilder<Customer>().name("customerReader").dataSource(dataSource)
        .sql("SELECT id, name, account_id FROM customer").beanRowMapper(Customer.class).build();
  }
}
