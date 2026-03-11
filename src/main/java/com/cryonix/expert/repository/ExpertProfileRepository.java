package com.cryonix.expert.repository;

import com.cryonix.expert.entity.ExpertProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertProfileRepository extends JpaRepository<ExpertProfile, Long> {

    @Query("SELECT e FROM ExpertProfile e WHERE LOWER(e.specialization) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<ExpertProfile> searchBySpecialization(@Param("keyword") String keyword, Pageable pageable);
}
