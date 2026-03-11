package com.cryonix.expert.service;

import com.cryonix.expert.dto.ExpertProfileDto;
import com.cryonix.expert.dto.ExpertProfileMapper;
import com.cryonix.expert.entity.ExpertProfile;
import com.cryonix.expert.repository.ExpertProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExpertService {

    private final ExpertProfileRepository expertProfileRepository;
    private final ExpertProfileMapper expertProfileMapper;

    @Transactional(readOnly = true)
    public Page<ExpertProfileDto> getAllExperts(Pageable pageable) {
        return expertProfileRepository.findAll(pageable)
                .map(expertProfileMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Page<ExpertProfileDto> searchExperts(String keyword, Pageable pageable) {
        Page<ExpertProfile> result;
        if (keyword == null || keyword.trim().isEmpty()) {
            result = expertProfileRepository.findAll(pageable);
        } else {
            result = expertProfileRepository.searchBySpecialization(keyword, pageable);
        }
        return result.map(expertProfileMapper::toDto);
    }

    @Transactional
    public ExpertProfileDto createExpert(ExpertProfileDto expertProfileDto) {
        ExpertProfile expertProfile = expertProfileMapper.toEntity(expertProfileDto);
        ExpertProfile savedExpert = expertProfileRepository.save(expertProfile);
        return expertProfileMapper.toDto(savedExpert);
    }
}
