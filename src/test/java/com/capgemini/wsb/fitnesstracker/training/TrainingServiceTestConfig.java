package com.capgemini.wsb.fitnesstracker.training;

import com.capgemini.wsb.fitnesstracker.training.internal.TrainingMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TrainingServiceTestConfig {

    @Bean
    public TrainingMapper trainingMapper() {
        return new TrainingMapper();
    }

}