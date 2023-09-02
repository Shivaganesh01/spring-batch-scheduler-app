package com.example.app.batch;

import com.example.app.model.EmployeeDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@StepScope
public class EmployeeDetailLogWriter implements ItemWriter<EmployeeDetail> {
    @Override
    public void write(Chunk<? extends EmployeeDetail> chunk) throws Exception {
        log.info("Chunk Size: {}, UserData: {}", chunk.size(), chunk.getUserData());
        for (EmployeeDetail emp: chunk.getItems()) {
            log.info("Employee Detail: {}", emp);
        }
    }
}