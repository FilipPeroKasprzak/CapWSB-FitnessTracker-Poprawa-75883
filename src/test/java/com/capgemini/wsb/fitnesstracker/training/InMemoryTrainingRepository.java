package com.capgemini.wsb.fitnesstracker.training;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryTrainingRepository implements TrainingRepository {

    private Map<Long, Training> trainingData = new HashMap<>();
    private long currentId = 1;

    @Override
    public Optional<Training> findAllByUserId(Long id) {
        return Optional.ofNullable(trainingData.get(id));
    }

    @Override
    public Training save(Training training) {
        if (training.getId() == null) {
            training.setId(currentId++);
        }
        trainingData.put(training.getId(), training);
        return training;
    }

}