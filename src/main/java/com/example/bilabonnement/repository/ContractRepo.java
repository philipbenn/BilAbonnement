package com.example.bilabonnement.repository;

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

    public List<ContractDTO> getActiveContracts() {
        //Find sql query under stored procedures
        String sql = """
                SELECT
                    c.contract_id,
                    cu.customer_id,
                    cu.customer_name,
                    ca.car_id,
                    cm.car_model_id,
                    cm.car_model_name,
                    ca.vognnummer,
                    clp.car_model_lease_period_plan_id,
                    clp.type AS lease_type,
                    clp.price_per_month AS lease_price,
                    ckp.car_model_max_km_plan_id,
                    ckp.max_km,
                    ckp.km_price_per_month AS km_plan_price,
                    c.start_date,
                    c.end_date,
                    (clp.price_per_month + ckp.km_price_per_month) AS total_price_per_month
                FROM
                    contract c
                   \s
                JOIN
                    customer cu ON c.customer_id = cu.customer_id
                JOIN
                    car ca ON c.car_id = ca.car_id
                JOIN
                    car_model cm ON ca.car_model_id = cm.car_model_id
                JOIN
                    car_model_lease_period_plan clp ON c.car_model_lease_period_plan_id = clp.car_model_lease_period_plan_id
                JOIN
                    car_model_max_km_plan ckp ON c.car_model_max_km_plan = ckp.car_model_max_km_plan_id
                   \s
                    WHERE CURDATE() BETWEEN start_date AND end_date-1
                   \s
                    ORDER BY end_date
                   \s
                """
                ;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractDTO.class));
    }
    public List<ContractDTO> getEndedContracts() {
        //Find sql query under stored procedures
        String sql = """
                SELECT
                    c.contract_id,
                    cu.customer_id,
                    cu.customer_name,
                    ca.car_id,
                    cm.car_model_id,
                    cm.car_model_name,
                    ca.vognnummer,
                    clp.car_model_lease_period_plan_id,
                    clp.type AS lease_type,
                    clp.price_per_month AS lease_price,
                    ckp.car_model_max_km_plan_id,
                    ckp.max_km,
                    ckp.km_price_per_month AS km_plan_price,
                    c.start_date,
                    c.end_date,
                    (clp.price_per_month + ckp.km_price_per_month) AS total_price_per_month
                FROM
                    contract c
                JOIN
                    customer cu ON c.customer_id = cu.customer_id
                JOIN
                    car ca ON c.car_id = ca.car_id
                JOIN
                    car_model cm ON ca.car_model_id = cm.car_model_id
                JOIN
                    car_model_lease_period_plan clp ON c.car_model_lease_period_plan_id = clp.car_model_lease_period_plan_id
                JOIN
                    car_model_max_km_plan ckp ON c.car_model_max_km_plan = ckp.car_model_max_km_plan_id
                    WHERE end_date <= current_date()
                    ORDER BY end_date;""";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractDTO.class));
    }
    public List<ContractDTO> getFutureContracts(){
        String sql = """
                SELECT
                    c.contract_id,
                    cu.customer_id,
                    cu.customer_name,
                    ca.car_id,
                    cm.car_model_id,
                    cm.car_model_name,
                    ca.vognnummer,
                    clp.car_model_lease_period_plan_id,
                    clp.type AS lease_type,
                    clp.price_per_month AS lease_price,
                    ckp.car_model_max_km_plan_id,
                    ckp.max_km,
                    ckp.km_price_per_month AS km_plan_price,
                    c.start_date,
                    c.end_date,
                    (clp.price_per_month + ckp.km_price_per_month) AS total_price_per_month
                FROM
                    contract c
                JOIN
                    customer cu ON c.customer_id = cu.customer_id
                JOIN
                    car ca ON c.car_id = ca.car_id
                JOIN
                    car_model cm ON ca.car_model_id = cm.car_model_id
                JOIN
                    car_model_lease_period_plan clp ON c.car_model_lease_period_plan_id = clp.car_model_lease_period_plan_id
                JOIN
                    car_model_max_km_plan ckp ON c.car_model_max_km_plan = ckp.car_model_max_km_plan_id
                    WHERE start_date > current_date()
                    ORDER BY start_date;""";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractDTO.class));
    }
    public List<ContractDTO> getCustomerHistory(int customer_id){
        String sql = """
                SELECT  car.car_id, car.car_model_id, car.vognnummer, car_model.car_model_name, car_model_lease_period_plan.type,contract.contract_id, contract.start_date, contract.end_date, contract.employee_id, contract.customer_id
                FROM contract
                JOIN car\s
                ON contract.car_id = car.car_id
                JOIN car_model
                ON car.car_model_id = car_model.car_model_id
                JOIN car_model_lease_period_plan\s
                ON car_model_lease_period_plan.car_model_lease_period_plan_id = contract.car_model_lease_period_plan_id
                WHERE customer_id = ? ORDER BY contract_id;""";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractDTO.class), customer_id);
    }
    public List<ContractDTO> editContract(int contract_id){
        String sql = "SELECT c.contract_id, cu.customer_id, cu.customer_name, ca.car_id, cm.car_model_id, cm.car_model_name, " +
                "ca.vognnummer, clp.car_model_lease_period_plan_id, clp.type AS lease_type," +
                " clp.price_per_month AS lease_price," +
                " ckp.car_model_max_km_plan_id, ckp.max_km, ckp.km_price_per_month AS km_plan_price," +
                " c.start_date, c.end_date, (clp.price_per_month + ckp.km_price_per_month) AS total_price_per_month " +
                "FROM contract c " +
                "JOIN customer cu " +
                "ON c.customer_id = cu.customer_id " +
                "JOIN car ca " +
                "ON c.car_id = ca.car_id " +
                "JOIN car_model cm " +
                "ON ca.car_model_id = cm.car_model_id " +
                "JOIN car_model_lease_period_plan clp " +
                "ON c.car_model_lease_period_plan_id = clp.car_model_lease_period_plan_id " +
                "JOIN car_model_max_km_plan ckp " +
                "ON c.car_model_max_km_plan = ckp.car_model_max_km_plan_id " +
                "WHERE contract_id = ? ORDER BY contract_id;";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractDTO.class), contract_id);
    }
    public List<ContractDTO> expiringContracts(){

        String sql = """
                SELECT\s
                    car.vognnummer,\s
                    car_model.car_model_name,\s
                    customer.customer_name,\s
                    (car_model_lease_period_plan.price_per_month + car_model_max_km_plan.km_price_per_month) AS total_price_per_month,\s
                    contract_id,
                    end_date
                FROM\s
                    contract
                JOIN\s
                    car ON contract.car_id = car.car_id
                JOIN\s
                    car_model ON car.car_model_id = car_model.car_model_id
                JOIN\s
                    customer ON contract.customer_id = customer.customer_id
                JOIN\s
                    car_model_lease_period_plan ON contract.car_model_lease_period_plan_id = car_model_lease_period_plan.car_model_lease_period_plan_id
                JOIN\s
                    car_model_max_km_plan ON contract.car_model_max_km_plan = car_model_max_km_plan.car_model_max_km_plan_id
                WHERE\s
                    contract.start_date >= CURDATE() AND contract.end_date > CURDATE()
                ORDER BY\s
                    contract.end_date LIMIT 8;
                """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractDTO.class));
    }

    // Other Lists
    public List<ContractTypeCount> contractTypeCounts() {
        String sql = """
                SELECT clpp.type, COUNT(*) as count
                FROM contract c
                JOIN car_model_lease_period_plan clpp
                ON c.car_model_lease_period_plan_id = clpp.car_model_lease_period_plan_id
                GROUP BY clpp.type;
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ContractTypeCount.class));
    }

    // Update methods
    public void setEndDateToToday(int contract_id){
        String sql = "UPDATE contract SET end_date = CURDATE() WHERE contract_id = ?";
        jdbcTemplate.update(sql, contract_id);
    }
    public void updateEndDate(){
        String sql = """
                UPDATE contract c
                JOIN car_model_lease_period_plan clp ON clp.car_model_lease_period_plan_id = c.car_model_lease_period_plan_id
                SET c.end_date = DATE_ADD(c.start_date, INTERVAL clp.nr_of_months MONTH)
                WHERE c.contract_id = ?;""";
        jdbcTemplate.update(sql, getNewestContractId());
    }
    public void updateStartAndEndDate(int contract_id, String contract_start_date, String contract_end_date){
        String sql = "UPDATE contract SET start_date = ?, end_date= ? WHERE contract_id = ?";
        jdbcTemplate.update(sql, contract_start_date, contract_end_date, contract_id);
    }

    // Insert methods
    public void addContract(int car_id, int customer_id, int car_model_lease_period_plan_id, int car_model_max_km_plan, String start_date, int employee_id){
        String sql = "INSERT INTO contract (car_id, customer_id, car_model_lease_period_plan_id, car_model_max_km_plan, start_date, employee_id, end_date) VALUES (?, ?, ?, ?, ?, ?, '2001-01-01')";
        jdbcTemplate.update(sql, car_id, customer_id, car_model_lease_period_plan_id, car_model_max_km_plan, start_date, employee_id);
        updateEndDate();
    }

    // Key values
    public Double monthlyIncome(){
        String sql = """
                SELECT SUM(cpp.price_per_month + cmkp.km_price_per_month) AS total_monthly_income
                FROM contract AS c
                JOIN car_model_lease_period_plan AS cpp ON c.car_model_lease_period_plan_id = cpp.car_model_lease_period_plan_id
                JOIN car_model_max_km_plan AS cmkp ON c.car_model_max_km_plan = cmkp.car_model_max_km_plan_id
                WHERE c.start_date <= CURDATE() AND (c.end_date > CURDATE())
                """;
        return jdbcTemplate.queryForObject(sql, Double.class);
    }
    public int getNewestContractId(){
        String sql = "SELECT MAX(contract_id) FROM contract";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public int countAllCars(){
        String sql = "SELECT COUNT(*) FROM car";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public int activeContracts(){
        String sql = """
                SELECT COUNT(*)
                FROM contract
                WHERE CURDATE() BETWEEN start_date AND end_date-1;
                """;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public int nrOfCarsInRepair(){
        String sql= """
                SELECT COUNT(DISTINCT car_return_report_id) AS 'Number of Cars in Repair'
                FROM car_return_damage
                WHERE isFixed=0;""";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
