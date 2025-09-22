package com.craftlink.backend.location.application.query;

import com.craftlink.backend.location.application.port.in.query.getAllCities.GetAllCitiesUseCase;
import com.craftlink.backend.location.application.port.in.query.shared.CityView;
import com.craftlink.backend.location.application.port.out.read.LocationQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllCitiesHandler implements GetAllCitiesUseCase {

  private final LocationQueryRepository locationQueryRepository;

  @Override
  public List<CityView> handle() {
    return locationQueryRepository.findAllCities();
  }
}
