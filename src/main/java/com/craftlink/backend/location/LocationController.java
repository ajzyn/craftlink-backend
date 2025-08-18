package com.craftlink.backend.location;

import com.craftlink.backend.location.dtos.CityDto;
import com.craftlink.backend.location.services.LocationService;
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


    private final LocationService locationService;

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> getCities() {
        return ResponseEntity.ok(locationService.getAllCities());
    }

    @GetMapping("{city}/districts")
    public ResponseEntity<List<String>> getCities(@PathVariable String city) {
        return ResponseEntity.ok(locationService.getDistrictsForCity(city));
    }
}
