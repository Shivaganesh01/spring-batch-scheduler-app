package com.example.app.batch;

import com.example.app.model.Employee;
import com.example.app.model.EmployeeDetail;
import com.example.app.model.PayScale;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProcessor implements ItemProcessor<Employee, EmployeeDetail> {

    public static final String EMP = "EMP-";

    @Override
    public EmployeeDetail process(Employee item) throws Exception {
        return EmployeeDetail.builder()
                .id(EMP +item.getId())
                .name(item.getName())
                .department(item.getDepartment())
                .payScale(gePayScale(item.getSalary()))
                .build();
    }

    private PayScale gePayScale(int salary) {
        if (salary > 2_00_000){
            return PayScale.SCALE_A;
        } else if (salary > 1_00_000) {
            return PayScale.SCALE_B;
        }else {
            return PayScale.SCALE_C;
        }
    }
}
