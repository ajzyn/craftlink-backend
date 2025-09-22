package com.craftlink.backend.location.application.port.in.query.getAllCities;

import com.craftlink.backend.location.application.port.in.query.shared.CityView;
import java.util.List;

public interface GetAllCitiesUseCase {

  List<CityView> handle();
}
