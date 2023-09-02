//package com.example.app.batch;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//
//import javax.sql.DataSource;
//
//@Configuration
//@Slf4j
//@PropertySource(value = "classpath:application.yml")
//public class DBConfig {
//
//    @Bean("dataSource")
//    @ConfigurationProperties(prefix = "h2.datasource")
//    @Primary
//    public DataSource dataSource(){
//        log.info("Inside h2 data source");
//        DataSource da = DataSourceBuilder.create().build();
//        log.info("{}",da);
//        return da;
//    }
//
//    @Bean("h2Jdbc")
//    @Primary
//    public NamedParameterJdbcTemplate getJdbcTemplate() {
//        return new NamedParameterJdbcTemplate(dataSource());
//    }
//}
