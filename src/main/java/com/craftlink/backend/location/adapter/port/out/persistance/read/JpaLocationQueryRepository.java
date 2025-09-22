package com.craftlink.backend.location.adapter.port.out.persistance.read;

import com.craftlink.backend.infrastructure.exceptions.custom.ConfigurationException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import com.craftlink.backend.location.application.port.in.query.shared.CityView;
import com.craftlink.backend.location.application.port.out.read.LocationQueryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaLocationQueryRepository implements LocationQueryRepository {

  private final ObjectMapper objectMapper;
  private List<CityView> cities;
  private Map<String, List<String>> districts;

  @PostConstruct
  private void loadData() {
    this.loadCities();
    this.loadDistricts();
  }

  @Override
  public List<CityView> findAllCities() {
    return cities;
  }

  @Override
  public boolean cityExists(String cityName) {
    return cities.stream().anyMatch(c -> c.name().equals(cityName));
  }

  @Override
  public List<String> findDistrictsByCity(String cityName) {
    return districts.getOrDefault(cityName, List.of());
  }


  private void loadCities() {
    try {
      var citiesResource = new ClassPathResource("static/locations/cities.json");
      var typeRef = new TypeReference<List<CityView>>() {
      };

      var rawCities = objectMapper.readValue(citiesResource.getInputStream(), typeRef);

      this.cities = rawCities.stream()
          .map(c -> new CityView(c.name().toLowerCase(), c.hasDistricts())).toList();
    } catch (IOException e) {
      throw new ConfigurationException(ExceptionCode.FAILED_TO_LOAD_FILE, e);
    }
  }

  private void loadDistricts() {
    try {
      var districtsResource = new ClassPathResource("static/locations/districts.json");
      var typeRef = new TypeReference<Map<String, List<String>>>() {
      };

      var rawDistricts = objectMapper.readValue(districtsResource.getInputStream(), typeRef);

      this.districts = rawDistricts.entrySet().stream().collect(Collectors.toMap(
          e -> e.getKey().toLowerCase(),
          Map.Entry::getValue
      ));
    } catch (IOException e) {
      throw new ConfigurationException(ExceptionCode.FAILED_TO_LOAD_FILE, e);
    }
  }
}
