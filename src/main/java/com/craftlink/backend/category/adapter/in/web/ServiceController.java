package com.craftlink.backend.category.adapter.in.web;

import com.craftlink.backend.category.adapter.in.web.dto.ServiceDetailsResponseDto;
import com.craftlink.backend.category.adapter.in.web.dto.ServiceSummaryResponseDto;
import com.craftlink.backend.category.adapter.in.web.mapper.ServiceWebMapper;
import com.craftlink.backend.category.application.port.in.query.getServiceDetails.GetServiceDetailsUseCase;
import com.craftlink.backend.category.application.port.in.query.getServiceSummaries.GetServiceSummariesUseCase;
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

  private final GetServiceSummariesUseCase getServiceSummaries;
  private final GetServiceDetailsUseCase getServiceDetails;
  private final ServiceWebMapper mapper;

  @GetMapping()
  public ResponseEntity<List<ServiceSummaryResponseDto>> getBasicServiceList(@RequestParam String searchPhrase) {
    var services = getServiceSummaries.handle(searchPhrase);
    return ResponseEntity.ok(mapper.toDto(services));
  }

  @GetMapping("/{slug}")
  public ResponseEntity<ServiceDetailsResponseDto> getServiceDetails(@PathVariable String slug) {
    var service = getServiceDetails.handle(slug);
    return ResponseEntity.ok(mapper.toDto(service));
  }
}
