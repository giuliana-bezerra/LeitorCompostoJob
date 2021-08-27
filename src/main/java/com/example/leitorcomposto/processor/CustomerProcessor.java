package com.example.leitorcomposto.processor;

import com.example.leitorcomposto.domain.Customer;
import com.example.leitorcomposto.domain.CustomerAccount;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerProcessor implements ItemProcessor<Customer, CustomerAccount> {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public CustomerAccount process(Customer customer) throws Exception {
    CustomerAccount customerAccount = (CustomerAccount) jdbcTemplate.queryForObject(
        "SELECT * FROM customer_account WHERE id = ?", new Object[] { customer.getAccountId() },
        new BeanPropertyRowMapper(CustomerAccount.class));
    customerAccount.setCustomer(customer);
    return customerAccount;
  }

}
