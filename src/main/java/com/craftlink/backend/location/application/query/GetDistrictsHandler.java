package com.craftlink.backend.location.application.query;

import com.craftlink.backend.infrastructure.exceptions.custom.BusinessException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import com.craftlink.backend.location.application.port.in.query.getCityByName.GetDistrictsUseCase;
import com.craftlink.backend.location.application.port.out.read.LocationQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDistrictsHandler implements GetDistrictsUseCase {

  private final LocationQueryRepository locationQueryRepository;

  @Override
  public List<String> handle(String cityName) {
    if (!locationQueryRepository.cityExists(cityName)) {
      throw new BusinessException(ExceptionCode.RESOURCE_NOT_FOUND);
    }

    return locationQueryRepository.findDistrictsByCity(cityName);
  }
}
