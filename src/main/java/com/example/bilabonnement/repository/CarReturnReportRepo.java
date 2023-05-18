package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.carReturnReport.Car_Return_Damage;
import com.example.bilabonnement.model.carReturnReport.Car_Return_Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarReturnReportRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Car_Return_Report> getAllCarReturnReports (){
        String sql = "SELECT * from car_return_report";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Car_Return_Report.class));
    }

    public Car_Return_Report getCarReturnReport (int car_return_report_id){
        String sql = "SELECT * from car_return_report where car_return_report_id = ?";
        return  jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Car_Return_Report.class),car_return_report_id);
    }

    public List<Car_Return_Damage> carReturnDamageFromReport (int car_return_report_id){
        String sql = "SELECT * from car_return_damage WHERE car_return_report_id = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Car_Return_Damage.class),car_return_report_id);
    }

    public void editCarReturnDamage (Car_Return_Damage car_return_damage){
       String sql = "UPDATE car_return_damage SET damage_description = ?, isFixed = ?, price = ? WHERE car_return_damage_id = ?";
         jdbcTemplate.update(sql,car_return_damage.getDamage_description(),car_return_damage.getIsFixed(),car_return_damage.getPrice(),car_return_damage.getCar_return_damage_id());

          }

    public void addCarReturnDamage (Car_Return_Damage car_return_damage){
        String sql = "INSERT INTO car_return_damage (car_return_report_id, damage_description, isFixed, price) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,car_return_damage.getCar_return_report_id(),car_return_damage.getDamage_description(),car_return_damage.getIsFixed(),car_return_damage.getPrice());
    }


    public int getMaxCarReturnReportId (){
        String sql = "SELECT MAX(car_return_report_id) FROM car_return_report";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }
    public void addCarReturnReport (int contract_id, int car_id){
        String sql = "INSERT INTO car_return_report (contract_id, car_id, km_driven) VALUES (?, ?, 0)";
        jdbcTemplate.update(sql,contract_id,car_id);
    }

}
