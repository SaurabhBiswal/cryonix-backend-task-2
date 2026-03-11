package com.cryonix.expert.controller;

import com.cryonix.expert.dto.ExpertProfileDto;
import com.cryonix.expert.service.ExpertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experts")
@RequiredArgsConstructor
@Validated
@Tag(name = "Expert ExpertProfile API", description = "Endpoints for searching and managing consultation experts.")
public class ExpertController {

    private final ExpertService expertService;

    @GetMapping("/search")
    @Operation(summary = "Search experts by specialization", description = "Returns a paginated list of experts matching the keyword in their specialization.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved experts.")
    public ResponseEntity<Page<ExpertProfileDto>> searchExperts(
            @RequestParam(value = "q", required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(expertService.searchExperts(keyword, pageable));
    }

    @GetMapping
    @Operation(summary = "Get all experts", description = "Returns a paginated list of all experts.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved experts.")
    public ResponseEntity<Page<ExpertProfileDto>> getAllExperts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(expertService.getAllExperts(pageable));
    }

    @PostMapping
    @Operation(summary = "Add a new expert", description = "Stores a new expert profile in the database. Requires authentication.")
    @ApiResponse(responseCode = "201", description = "Expert profile created successfully.")
    @ApiResponse(responseCode = "400", description = "Invalid input.")
    public ResponseEntity<ExpertProfileDto> createExpert(@Valid @RequestBody ExpertProfileDto expertProfileDto) {
        ExpertProfileDto savedExpert = expertService.createExpert(expertProfileDto);
        return new ResponseEntity<>(savedExpert, HttpStatus.CREATED);
    }
}
