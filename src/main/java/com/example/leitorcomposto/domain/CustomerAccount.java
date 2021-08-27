package com.example.leitorcomposto.domain;

public class CustomerAccount {
  private int id;
  private int agencia;
  private int conta;
  private Customer customer;

  public CustomerAccount() {
  }

  public CustomerAccount(int id, int agencia, int conta) {
    this.id = id;
    this.agencia = agencia;
    this.conta = conta;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAgencia() {
    return agencia;
  }

  public void setAgencia(int agencia) {
    this.agencia = agencia;
  }

  public int getConta() {
    return conta;
  }

  public void setConta(int conta) {
    this.conta = conta;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public String toString() {
    return "CustomerAccount [agencia=" + agencia + ", conta=" + conta + ", customer=" + customer + ", id=" + id + "]";
  }

}
