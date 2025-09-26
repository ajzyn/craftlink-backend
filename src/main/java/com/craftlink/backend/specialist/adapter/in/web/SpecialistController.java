package com.craftlink.backend.specialist.adapter.in.web;

import com.craftlink.backend.specialist.adapter.in.web.dto.SpecialistProfileResponseDto;
import com.craftlink.backend.specialist.adapter.in.web.dto.UpdateSpecialistProfileRequestDto;
import com.craftlink.backend.specialist.adapter.in.web.mapper.SpecialistWebMapper;
import com.craftlink.backend.specialist.application.port.in.command.updateUserProfile.UpdateUserProfileUseCase;
import com.craftlink.backend.specialist.application.port.in.query.getMySpecialistProfile.GetMySpecialistProfileUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sec/specialist")
public class SpecialistController {

  private final SpecialistWebMapper mapper;
  private final UpdateUserProfileUseCase updateUserProfile;
  private final GetMySpecialistProfileUseCase getSpecialistDetails;

  @PreAuthorize("@authz.isSpecialist()")
  @PostMapping("/update-profile")
  public ResponseEntity<Void> updateSpecialist(@Valid @RequestBody UpdateSpecialistProfileRequestDto dto) {
    var cmd = mapper.toCommand(dto);
    updateUserProfile.handle(cmd);

    return ResponseEntity.ok().build();
  }

  @PreAuthorize("@authz.isSpecialist()")
  @GetMapping("/me")
  public ResponseEntity<SpecialistProfileResponseDto> getMySpecialistProfile() {
    var result = getSpecialistDetails.handle();
    
  }
}
