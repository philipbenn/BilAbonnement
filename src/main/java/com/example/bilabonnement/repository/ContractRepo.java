package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.Contract;
import com.example.bilabonnement.model.ContractInfo;
import com.example.bilabonnement.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractRepo {

    @Autowired
    JdbcTemplate template;


    public List<ContractInfo> getAllContractInfo() {
        //Find sql query under stored procedeus
        String sql = "CALL get_all_contract_info";
        return template.query(sql, new BeanPropertyRowMapper<>(ContractInfo.class));
    }
}