package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.carReturnReport.Car_Return_Damage;
import com.example.bilabonnement.model.carReturnReport.Car_Return_Report;
import com.example.bilabonnement.model.carReturnReport.Car_Return_Report_DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarReturnReportRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // Lists
    public List<Car_Return_Report> getAllCarReturnReports (){
        String sql = "SELECT * from car_return_report";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Car_Return_Report.class));
    }
    public List<Car_Return_Damage> carReturnDamageFromReport (int car_return_report_id){
        String sql = "SELECT * from car_return_damage WHERE car_return_report_id = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Car_Return_Damage.class),car_return_report_id);
    }

    public List <Car_Return_Report_DTO> getAllPendingCarReturnReports(){
        String sql = """
                SELECT car_return_report.car_return_report_id, car_return_report.contract_id, car.vognnummer, car_model.car_model_name, COUNT(car_return_damage.car_return_damage_id) AS nrOfDamages
                FROM car_return_report
                JOIN car_return_damage
                ON car_return_report.car_return_report_id = car_return_damage.car_return_report_id
                JOIN car\s
                ON car_return_report.car_id = car.car_id
                JOIN car_model\s
                ON car.car_model_id = car_model.car_model_id
                WHERE car_return_damage.isFixed = 0
                GROUP BY car_return_report.car_return_report_id, car_return_report.contract_id, car.vognnummer, car_model.car_model_name;""";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Car_Return_Report_DTO.class));

    }

    public List <Car_Return_Report_DTO> getAllClosedCarReturnReports(){
        String sql = """
                SELECT car_return_report.car_return_report_id, car_return_report.contract_id, car.vognnummer, car_model.car_model_name, COUNT(car_return_damage.car_return_damage_id) AS nrOfDamages
                FROM car_return_report
                JOIN car_return_damage
                ON car_return_report.car_return_report_id = car_return_damage.car_return_report_id
                JOIN car\s
                ON car_return_report.car_id = car.car_id
                JOIN car_model\s
                ON car.car_model_id = car_model.car_model_id
                WHERE car_return_damage.isFixed = 1
                GROUP BY car_return_report.car_return_report_id, car_return_report.contract_id, car.vognnummer, car_model.car_model_name;""";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Car_Return_Report_DTO.class));

    }

    // Insert methods
    public void addCarReturnReport (int contract_id, int car_id){
        String sql = "INSERT INTO car_return_report (contract_id, car_id, km_driven) VALUES (?, ?, 0)";
        jdbcTemplate.update(sql,contract_id,car_id);
    }
    public void addCarReturnDamage (Car_Return_Damage car_return_damage){
        String sql = "INSERT INTO car_return_damage (car_return_report_id, damage_description, isFixed, price) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,car_return_damage.getCar_return_report_id(),car_return_damage.getDamage_description(),car_return_damage.getIsFixed(),car_return_damage.getPrice());
    }

    // Update Methods
    public void editCarReturnDamage (Car_Return_Damage car_return_damage){
       String sql = "UPDATE car_return_damage SET damage_description = ?, isFixed = ?, price = ? WHERE car_return_damage_id = ?";
         jdbcTemplate.update(sql,car_return_damage.getDamage_description(),car_return_damage.getIsFixed(),car_return_damage.getPrice(),car_return_damage.getCar_return_damage_id());

          }

    // Key Values
    public int getMaxCarReturnReportId (){
        String sql = "SELECT MAX(car_return_report_id) FROM car_return_report";
        return jdbcTemplate.queryForObject(sql,Integer.class);
    }


}
