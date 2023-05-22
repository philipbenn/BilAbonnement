package com.example.bilabonnement.model.customer;


public class Customer {
    private Integer customer_id;
    private String customer_name;
    private Integer number_of_contracts;
    Customer(){

    }

    public Integer getNumber_of_contracts() {
        return number_of_contracts;
    }

    public void setNumber_of_contracts(Integer number_of_contracts) {
        this.number_of_contracts = number_of_contracts;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }


}
