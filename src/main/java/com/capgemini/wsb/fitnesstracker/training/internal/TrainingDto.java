package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**

 DTO for training.
 Used to transfer data of training between logic and presentation layers.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDto {
    private Long id;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private ActivityType activityType;
    private double distance;
    private double averageSpeed;
}