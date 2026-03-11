package com.cryonix.expert.config;

import com.cryonix.expert.entity.ExpertProfile;
import com.cryonix.expert.repository.ExpertProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ExpertProfileRepository expertProfileRepository;

    @Override
    public void run(String... args) {
        if (expertProfileRepository.count() == 0) {
            List<ExpertProfile> experts = List.of(
                    ExpertProfile.builder().name("Rahul Sharma").specialization("Career Guidance").consultationFee(500.0).available(true).build(),
                    ExpertProfile.builder().name("Sneha Gupta").specialization("UI/UX Design").consultationFee(1200.0).available(true).build(),
                    ExpertProfile.builder().name("Amit Patel").specialization("Java Backend Development").consultationFee(1500.0).available(false).build(),
                    ExpertProfile.builder().name("Priya Singh").specialization("Data Science").consultationFee(2000.0).available(true).build(),
                    ExpertProfile.builder().name("Vikram Mehta").specialization("Digital Marketing").consultationFee(800.0).available(true).build()
            );
            expertProfileRepository.saveAll(experts);
            System.out.println("Inserted 5 sample expert profiles.");
        }
    }
}
