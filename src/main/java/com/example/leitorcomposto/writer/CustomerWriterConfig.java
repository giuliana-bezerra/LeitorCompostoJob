package com.example.leitorcomposto.writer;

import com.example.leitorcomposto.domain.CustomerAccount;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerWriterConfig {
  @Bean
  public ItemWriter<CustomerAccount> customerWriter() {
    return items -> items.forEach(System.out::println);
  }
}
