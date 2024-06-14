package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface TrainingProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<User> getTraining(Long trainingId);

    /**
     * Retrieves a training based on its ID, to delete it.
     * If the given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param trainingId - the id of the training marked for deletion.
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    void deleteTrainingByUserId(Long trainingId);

    /**
     * a List, that will search for us all available trainings
     *
     * @return List<Training> containing all Trainings currently available.
     */
    public List<Training> getAllTrainings();

    /**
     *  A list, that will search all the trainings for ONE user.
     *
     * @param userId - id of the user we search trainings for
     * @return all trainings the user has participated in.
     */
    public List<Training> getAllTrainingsForUser(Long userId);

    /**
     * A list, that will return for us all finished trainings.
     *
     * @param afterTime - checks for all finished trainings based on afterTime.
     * @return list of all trainings that passed the time set in afterTime.
     */
    public List<Training> getAllFinishedTrainings(LocalDateTime afterTime);

    /**
     * A list, that will give us all trainings, based on the activity type we've set.
     *
     * @param activityType - activityType we are interested in.
     * @return list of all trainings for the specified activityType
     */
    public List<Training> getAllTrainingsByActivityType(ActivityType activityType);

    /**
     * Create a new training
     *
     * @param trainingRequest - searches for trainingRequest, to create a new training regiment.
     * @return the newly created training.
     */
    public Training createTraining(TrainingRequest trainingRequest);

    /**
     * Updating a already existing training.
     *
     * @param trainingId - id of the training we want to update
     * @param updatedTraining - all the informations about the newly updated training
     * @return the updated training.
     */
    public Training updateTraining(Long trainingId, Training updatedTraining);
}
