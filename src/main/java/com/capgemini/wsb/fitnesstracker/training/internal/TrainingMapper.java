package com.capgemini.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;


@Component
public class TrainingMapper {


    private UserProvider userProvider;

    /**
     * Mapping data about trainings.
     *
     * @param existingTraining - Sets up existing training method
     * @param updatedTraining - Sets up existing updated training method
     *
     */
    public void updateTrainingFromDto(Training existingTraining, Training updatedTraining) {
        existingTraining.setStartTime(updatedTraining.getStartTime());
        existingTraining.setEndTime(updatedTraining.getEndTime());
        existingTraining.setActivityType(updatedTraining.getActivityType());
        existingTraining.setDistance(updatedTraining.getDistance());
        existingTraining.setAverageSpeed(updatedTraining.getAverageSpeed());
    }

    /**
     *
     * Converts a {@link Training} object to a {@link TrainingDto}.
     * @param training The training object to be converted.
     * @return The corresponding {@link TrainingDto} object.
     *
     */

    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                training.getUser().getId(),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed());}

    /**
     * Converts a {@link TrainingDto} to a {@link Training} entity.
     * @param trainingDto The {@link TrainingDto} to be converted.
     * @return The corresponding {@link Training} entity.
     */
    Training toEntity(TrainingDto trainingDto) {
        return userProvider.getUser(trainingDto.getUserId()).map(user -> new Training(
                user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                trainingDto.getActivityType(),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed())).orElseThrow(() -> new IllegalArgumentException("User not found"));}

}