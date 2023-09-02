package com.example.app.batch;

import com.example.app.model.Employee;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Employee.builder()
                .id(rs.getInt(1))
                .name(rs.getString(2)) //rs.getString("name")
                .department(rs.getString(3))
                .salary(rs.getInt(4))
                .build();
    }
}
