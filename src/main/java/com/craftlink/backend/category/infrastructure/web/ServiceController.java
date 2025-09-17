package com.craftlink.backend.category.infrastructure.web;

import com.craftlink.backend.service.dtos.ServiceBasicDto;
import com.craftlink.backend.service.dtos.ServiceDetailsDto;
import com.craftlink.backend.service.services.ServiceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services")
public class ServiceController {

  private final ServiceService serviceService;

  @GetMapping()
  public ResponseEntity<List<ServiceBasicDto>> getBasicServiceList(@RequestParam String searchPhrase) {
    return ResponseEntity.ok(serviceService.getBasicServiceList(searchPhrase));
  }

  @GetMapping("/{slug}")
  public ResponseEntity<ServiceDetailsDto> getServiceDetails(@PathVariable String slug) {
    return ResponseEntity.ok(serviceService.getServiceDetailsBySlug(slug));
  }
}
