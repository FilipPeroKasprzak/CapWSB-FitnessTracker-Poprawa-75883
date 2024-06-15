package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingRequest;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingMapper;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
// TODO: Provide Impl
public class TrainingServiceImpl implements TrainingProvider {

    @Autowired
    private final TrainingRepository trainingRepository;

    @Autowired
    private final TrainingMapper trainingMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public TrainingServiceImpl(TrainingRepository trainingRepository, UserRepository userRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public Optional<User> getTraining(final Long trainingId) {
        throw new UnsupportedOperationException("Not finished yet");
    }

    @Override
    public void deleteTrainingByUserId(Long userId) {
        Optional<Training> first = trainingRepository.findAll().stream().filter(training -> Objects.equals(training.getUser(), userId)).findFirst();
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getAllTrainingsForUser(Long userId) {
        return trainingRepository.findAllByUserId(userId);
    }

    @Override
    public List<Training> getAllFinishedTrainings(LocalDateTime afterTime) {
        return trainingRepository.findAllByEndTimeAfter(afterTime);
    }

    @Override
    public List<Training> getAllTrainingsByActivityType(ActivityType activityType) {
        return trainingRepository.findAllByActivityType(activityType);
    }

    @Override
    public Training createTraining(TrainingRequest trainingRequest) {
        User user = userRepository.findById(trainingRequest.userId()).orElseThrow(() -> new UserNotFoundException(trainingRequest.userId()));
        Training training = new Training(user, trainingRequest.startTime(), trainingRequest.endTime(), trainingRequest.activityType(), trainingRequest.distance(), trainingRequest.averageSpeed());
        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long trainingId, Training updatedTraining) {
        Optional<Training> existingTrainingOpt = trainingRepository.findById(trainingId);
        if (existingTrainingOpt.isPresent()) {
            Training existingTraining = existingTrainingOpt.get();
            trainingMapper.updateTrainingFromDto(existingTraining, updatedTraining);
            return trainingRepository.save(existingTraining);
        } else {
            throw new TrainingNotFoundException(trainingId);
        }
    }
}