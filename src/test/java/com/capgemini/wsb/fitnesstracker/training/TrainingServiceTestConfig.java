package com.capgemini.wsb.fitnesstracker.training;

import com.capgemini.wsb.fitnesstracker.training.internal.TrainingMapper;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingRepository;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TrainingServiceTestConfig {

    @Bean
    public TrainingMapper trainingMapper() {
        return new TrainingMapper();
    }

    @Bean
    public TrainingRepository trainingRepository() {
        return new InMemoryTrainingRepository();
    }

    @Bean
    public TrainingServiceImpl trainingService() {
        return new TrainingServiceImpl(trainingRepository(), trainingMapper());
    }
}