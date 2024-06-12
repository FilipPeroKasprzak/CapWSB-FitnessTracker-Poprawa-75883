package com.capgemini.wsb.fitnesstracker.statistics.api;


import com.capgemini.wsb.fitnesstracker.statistics.internal.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService implements StatisticsProvider{

    private final StatisticsRepository statisticsRepository;

    @Autowired
    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public Optional<Statistics> getStatistics(Long statisticsId){
        return statisticsRepository.findById(statisticsId);
    }
    public List<Statistics> getAllStatistics() {
        return statisticsRepository.findAll();
    }
    public Statistics createStatistics(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }
}