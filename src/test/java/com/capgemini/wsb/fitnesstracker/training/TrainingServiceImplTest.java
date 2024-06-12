package com.capgemini.wsb.fitnesstracker.training;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingRequest;
import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingRepository;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingServiceImpl;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {TrainingServiceImpl.class}))
public class TrainingServiceImplTest {

    @Autowired
    private TrainingServiceImpl trainingService;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private User user3;

    private Training training1;
    private Training training2;
    private Training training3;

    @BeforeEach
    public void setUp() {
        user1 = new User("Andrew", "Moris", LocalDate.of(1991, 5, 15), "andrew123@gmail.com");
        user2 = new User("Anna", "Black", LocalDate.of(1999, 9, 20), "anna.b@gmail.com");
        user3 = new User("Matias", "Nowak", LocalDate.of(1994, 1, 1), "m.nowak@gmail.com");

        Date startTime = new Date();
        Date endTime1 = new Date(startTime.getTime() + 15000);
        Date endTime2 = new Date(startTime.getTime() + 25000);
        Date endTime3 = new Date(startTime.getTime() + 35000);

        training1 = new Training(user1, startTime, endTime1, ActivityType.RUNNING, 5.0, 10.0);
        training1.setId(1L);

        training2 = new Training(user2, startTime, endTime2, ActivityType.CYCLING, 10.0, 15.0);
        training2.setId(2L);

        training3 = new Training(user3, startTime, endTime3, ActivityType.SWIMMING, 2.0, 2.5);
        training3.setId(3L);
    }

    @Test
    public void testShouldCreateTraining() {
        TrainingRequest trainingReq = new TrainingRequest(user1.getId(), new Date(), new Date(), ActivityType.RUNNING, 9.0, 13.0);
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        when(trainingRepository.save(any(Training.class))).thenReturn(training1);
        Training result = trainingService.createTraining(trainingReq);
        assertNotNull(result);
        assertEquals(training1, result);
        verify(userRepository, times(1)).findById(user1.getId());
        verify(trainingRepository, times(1)).save(any(Training.class));
    }

    @Test
    public void testShouldDeleteTrainingById() {
        when(trainingRepository.findAll()).thenReturn(Arrays.asList(training1, training2, training3));
        trainingService.deleteTrainingByUserId(user1.getId());
        verify(trainingRepository, times(1)).findAll();
        verify(trainingRepository, times(1)).delete(training1);
    }
}
