package com.example.leitorcomposto.reader;

import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import com.example.leitorcomposto.domain.Customer;
import com.example.leitorcomposto.domain.CustomerAccount;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class CustomerAccountPageProcessor implements PageProcessor<CustomerAccount> {
  private NamedParameterJdbcTemplate jdbcTemplate;

  public void setDataSource(DataSource dataSource) {
    jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
  }

  @Override
  public void process(List<CustomerAccount> page) {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("ids", page.stream().map(o -> o.getId()).collect(Collectors.toList()));
    List<Customer> customersAccounts = jdbcTemplate.query(
        "SELECT * FROM customer WHERE account_id in (:ids) ORDER BY account_id", parameters,
        new BeanPropertyRowMapper<Customer>(Customer.class));
    for (int i = 0; i < page.size(); i++) {
      page.get(i).setCustomer(customersAccounts.get(i));
    }
  }
}
