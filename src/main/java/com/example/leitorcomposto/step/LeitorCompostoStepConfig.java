package com.example.leitorcomposto.step;

import com.example.leitorcomposto.domain.Customer;
import com.example.leitorcomposto.domain.CustomerAccount;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LeitorCompostoStepConfig {
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Step leitorCompostoStep(ItemReader<CustomerAccount> reader, ItemWriter<CustomerAccount> writer) {
    return stepBuilderFactory.get("leitorCompostoStep").<CustomerAccount, CustomerAccount>chunk(10).reader(reader)
        .writer(writer).build();
  }
}
