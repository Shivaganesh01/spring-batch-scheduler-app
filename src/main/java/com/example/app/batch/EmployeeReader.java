package com.example.app.batch;

import com.example.app.model.Employee;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.H2PagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component("employeeReader")
@StepScope
public class EmployeeReader {

    @Autowired
    private DataSource dataSource;

    public JdbcPagingItemReader<Employee> getPaginationReader() {
        final JdbcPagingItemReader<Employee> reader = new JdbcPagingItemReader<>();
        final EmployeeRowMapper employeeMapper = new EmployeeRowMapper();
        reader.setDataSource(dataSource);
        reader.setFetchSize(2);
        reader.setPageSize(2);
        reader.setRowMapper(employeeMapper);
        reader.setQueryProvider(createQuery());
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dept", "IT");
        reader.setParameterValues(parameters);
        return reader;
    }

    private H2PagingQueryProvider createQuery() {
        final Map<String, Order> sortKeys = new HashMap<>();
        sortKeys.put("id", Order.ASCENDING);
        final H2PagingQueryProvider queryProvider = new H2PagingQueryProvider();
        queryProvider.setSelectClause("*");
        queryProvider.setFromClause("employee");
        queryProvider.setWhereClause("department = :dept");
        queryProvider.setSortKeys(sortKeys);
        return queryProvider;
    }
}
