package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/trainings")
public class TrainingController {

    @Autowired
    private TrainingServiceImpl trainingService;

    @GetMapping
    public ResponseEntity<List<Training>> getAllTrainings() {
        return ResponseEntity.ok(trainingService.getAllTrainings());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Training>> getTrainingById(@PathVariable Long userId) {
        return ResponseEntity.ok(trainingService.getAllTrainingsForUser(userId));
    }

    @GetMapping("/finished/{afterTime}")
    public ResponseEntity<List<Training>> getAllFinishedTrainings(@PathVariable String afterTime) {
        LocalDateTime afterDateTime = LocalDate.parse(afterTime).atStartOfDay();
        return ResponseEntity.ok(trainingService.getAllFinishedTrainings(afterDateTime));
    }

    @GetMapping("/activityType")
    public ResponseEntity<List<Training>> getTrainingByActivityType(@RequestParam ActivityType activityType) {
        return ResponseEntity.ok(trainingService.getAllTrainingsByActivityType(activityType));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Training createTraining(@RequestBody TrainingRequest trainingRequest){
        return trainingService.createTraining(trainingRequest);
    }

    @PutMapping("/{trainingId}")
        public ResponseEntity<Training> updateTraining(@PathVariable Long trainingId, @RequestBody Training training) {
        Training updatedTraining = trainingService.updateTraining(trainingId, training);
        return ResponseEntity.ok(updatedTraining);
    }
}
