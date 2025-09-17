package com.craftlink.backend.location.services;

import com.craftlink.backend.infrastructure.exceptions.custom.ConfigurationException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import com.craftlink.backend.location.dtos.CityDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

  private final ObjectMapper objectMapper;
  private List<CityDto> cities;
  private Map<String, List<String>> districts;

  public LocationService(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.loadData();
  }

  @PostConstruct
  private void loadData() {
    this.loadCities();
    this.loadDistricts();
  }

  public List<CityDto> getAllCities() {
    return cities;
  }

  public List<String> getDistrictsForCity(String city) {
    return districts.getOrDefault(city, List.of());
  }

  private void loadCities() {
    try {
      var citiesResource = new ClassPathResource("static/locations/cities.json");
      var typeRef = new TypeReference<List<CityDto>>() {
      };

      this.cities = objectMapper.readValue(citiesResource.getInputStream(), typeRef);
    } catch (IOException e) {
      throw new ConfigurationException(ExceptionCode.FAILED_TO_LOAD_FILE, e);
    }
  }

  private void loadDistricts() {
    try {
      var districtsResource = new ClassPathResource("static/locations/districts.json");
      var typeRef = new TypeReference<Map<String, List<String>>>() {
      };

      this.districts = objectMapper.readValue(districtsResource.getInputStream(), typeRef);
    } catch (IOException e) {
      throw new ConfigurationException(ExceptionCode.FAILED_TO_LOAD_FILE, e);
    }
  }
}
