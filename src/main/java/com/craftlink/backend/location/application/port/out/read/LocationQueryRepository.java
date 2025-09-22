package com.craftlink.backend.location.application.port.out.read;

import com.craftlink.backend.location.application.port.in.query.shared.CityView;
import java.util.List;

public interface LocationQueryRepository {

  List<CityView> findAllCities();

  boolean cityExists(String cityName);

  List<String> findDistrictsByCity(String cityName);
}
