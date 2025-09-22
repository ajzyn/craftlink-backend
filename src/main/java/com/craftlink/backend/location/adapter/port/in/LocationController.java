package com.craftlink.backend.location.adapter.port.in;

import com.craftlink.backend.location.adapter.port.in.dto.CityDto;
import com.craftlink.backend.location.adapter.port.in.mapper.LocationWebMapper;
import com.craftlink.backend.location.application.port.in.query.getAllCities.GetAllCitiesUseCase;
import com.craftlink.backend.location.application.port.in.query.getCityByName.GetDistrictsUseCase;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

  private final GetAllCitiesUseCase getAllCities;
  private final GetDistrictsUseCase getDistricts;
  private final LocationWebMapper mapper;

  @GetMapping("/cities")
  public ResponseEntity<List<CityDto>> getCities() {
    var cities = getAllCities.handle();
    return ResponseEntity.ok(mapper.toDto(cities));
  }

  @GetMapping("{city}/districts")
  public ResponseEntity<List<String>> getCities(@PathVariable @NotBlank String city) {
    return ResponseEntity.ok(getDistricts.handle(city));
  }
}
