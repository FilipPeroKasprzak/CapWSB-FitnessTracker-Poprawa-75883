package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import java.util.Date;

/**
 * Making records of a requested training.
 *
 * @param userId - records the ID of a user
 * @param startTime - records the starting time of the Training
 * @param endTime - records the time when the training ended
 * @param activityType - records, what type of Activity training revolved around
 * @param distance - records what distance was beaten during the training
 * @param averageSpeed - records the average speed of the user achieved in this training session
 */
public record TrainingRequest(Long userId, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
}
