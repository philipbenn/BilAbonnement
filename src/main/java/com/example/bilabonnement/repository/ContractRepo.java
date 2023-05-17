package com.example.bilabonnement.repository;

import com.example.bilabonnement.model.carModel.Car_Model_Lease_Period_Plan;
import com.example.bilabonnement.model.carModel.Car_Model_Max_Km_Plan;
import com.example.bilabonnement.model.carModel.Car_Model;
import com.example.bilabonnement.model.contract.Contract;
import com.example.bilabonnement.model.contract.ContractDTO;
import com.example.bilabonnement.model.contract.ContractTypeCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public ContractRepo() {
    }
    public List<ContractDTO> getAllContractInfo() {
        //Find sql query under stored procedures
        String sql = "CALL get_all_contract_info";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractDTO.class));
    }
    public List<Car_Model> getAllCarModels() {
        String sql = "SELECT * FROM car_model";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car_Model.class));
    }
    public List<Car_Model_Lease_Period_Plan> getAllCarModelLeasePeriodPlansForCarModel(int carModelId) {
        String sql = "SELECT * FROM car_model_lease_period_plan WHERE car_model_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car_Model_Lease_Period_Plan.class), carModelId);
    }
    public List <Car_Model_Max_Km_Plan> getAllCarModelMaxKmPlans(int carModelId) {
        String sql = "SELECT * FROM car_model_max_km_plan WHERE car_model_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Car_Model_Max_Km_Plan.class), carModelId);
    }
    public void addContract(int car_id, int customer_id, int car_model_lease_period_plan_id,
                            int car_model_max_km_plan, String start_date, int employee_id){
        String sql = "INSERT INTO contract (car_id, customer_id, car_model_lease_period_plan_id, car_model_max_km_plan, start_date, employee_id, end_date) VALUES (?, ?, ?, ?, ?, ?, '2001-01-01')";
        jdbcTemplate.update(sql, car_id, customer_id, car_model_lease_period_plan_id, car_model_max_km_plan, start_date, employee_id);
        updateEndDate();
    }
    public int getNewestContractId(){
        String sql = "SELECT MAX(contract_id) FROM contract";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public void updateEndDate(){
        String sql = "UPDATE contract c\n" +
                "JOIN car_model_lease_period_plan clp ON clp.car_model_lease_period_plan_id = c.car_model_lease_period_plan_id\n" +
                "SET c.end_date = DATE_ADD(c.start_date, INTERVAL clp.nr_of_months MONTH)\n" +
                "WHERE c.contract_id = ?;";
        jdbcTemplate.update(sql, getNewestContractId());
    }
    public List<ContractDTO> expiringContracts(){

        String sql = "SELECT \n" +
                "    car.vognnummer, \n" +
                "    car_model.car_model, \n" +
                "    customer.customer_name, \n" +
                "    (car_model_lease_period_plan.price_per_month + car_model_max_km_plan.km_price_per_month) AS total_price_per_month, \n" +
                "    contract_id,\n" +
                "    end_date\n" +
                "FROM \n" +
                "    contract\n" +
                "JOIN \n" +
                "    car ON contract.car_id = car.car_id\n" +
                "JOIN \n" +
                "    car_model ON car.car_model_id = car_model.car_model_id\n" +
                "JOIN \n" +
                "    customer ON contract.customer_id = customer.customer_id\n" +
                "JOIN \n" +
                "    car_model_lease_period_plan ON contract.car_model_lease_period_plan_id = car_model_lease_period_plan.car_model_lease_period_plan_id\n" +
                "JOIN \n" +
                "    car_model_max_km_plan ON contract.car_model_max_km_plan = car_model_max_km_plan.car_model_max_km_plan_id\n" +
                "WHERE \n" +
                "    contract.start_date >= CURDATE() AND contract.end_date > CURDATE()\n" +
                "ORDER BY \n" +
                "    contract.end_date ASC LIMIT 4;\n";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractDTO.class));
    }
    public int countAllCars(){
        String sql = "SELECT COUNT(*) FROM car";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public int activeContracts(){
        String sql = "SELECT COUNT(*)\n" +
                "FROM contract\n" +
                "WHERE CURDATE() BETWEEN start_date AND end_date;\n";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public int nrOfCarsInRepair(){
        String sql="SELECT COUNT(DISTINCT car_return_report_id) AS 'Number of Cars in Repair'\n" +
                "FROM car_return_damage\n" +
                "WHERE isFixed=0;";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public double monthlyIncome(){
        String sql = "SELECT SUM(cpp.price_per_month + cmkp.km_price_per_month) AS total_monthly_income\n" +
                "FROM contract AS c\n" +
                "JOIN car_model_lease_period_plan AS cpp ON c.car_model_lease_period_plan_id = cpp.car_model_lease_period_plan_id\n" +
                "JOIN car_model_max_km_plan AS cmkp ON c.car_model_max_km_plan = cmkp.car_model_max_km_plan_id\n" +
                "WHERE c.start_date <= '2023-05-12' AND (c.end_date > '2023-05-12' OR c.end_date IS NULL)\n";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }
    public List<ContractTypeCount> contractTypeCountDTOS() {
        String sql = "SELECT clpp.type, COUNT(*) as count\n" +
                "FROM contract c\n" +
                "JOIN car_model_lease_period_plan clpp\n" +
                "ON c.car_model_lease_period_plan_id = clpp.car_model_lease_period_plan_id\n" +
                "GROUP BY clpp.type;\n";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractTypeCount.class));
    }

    public List<ContractDTO> getCustomerHistory(int customer_id){
        String sql = "SELECT  car.car_id, car.car_model_id, car.vognnummer, car_model.car_model, car_model_lease_period_plan.type," +
                "contract.contract_id, contract.start_date, contract.end_date, contract.employee_id, contract.customer_id\n" +
                "FROM contract\n" +
                "JOIN car \n" +
                "ON contract.car_id = car.car_id\n" +
                "JOIN car_model\n" +
                "ON car.car_model_id = car_model.car_model_id\n" +
                "JOIN car_model_lease_period_plan \n" +
                "ON car_model_lease_period_plan.car_model_lease_period_plan_id = contract.car_model_lease_period_plan_id\n" +
                "WHERE customer_id = ? ORDER BY contract_id;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractDTO.class), customer_id);
    }

}
