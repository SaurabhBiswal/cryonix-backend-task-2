package com.cryonix.expert.dto;

import com.cryonix.expert.entity.ExpertProfile;
import org.springframework.stereotype.Component;

@Component
public class ExpertProfileMapper {

    public ExpertProfileDto toDto(ExpertProfile entity) {
        if (entity == null) return null;
        return ExpertProfileDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .specialization(entity.getSpecialization())
                .consultationFee(entity.getConsultationFee())
                .available(entity.isAvailable())
                .build();
    }

    public ExpertProfile toEntity(ExpertProfileDto dto) {
        if (dto == null) return null;
        return ExpertProfile.builder()
                .id(dto.getId())
                .name(dto.getName())
                .specialization(dto.getSpecialization())
                .consultationFee(dto.getConsultationFee())
                .available(dto.isAvailable())
                .build();
    }
}
