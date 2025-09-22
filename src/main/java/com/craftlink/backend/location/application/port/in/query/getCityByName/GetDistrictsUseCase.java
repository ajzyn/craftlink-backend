package com.craftlink.backend.location.application.port.in.query.getCityByName;

import java.util.List;

public interface GetDistrictsUseCase {

  List<String> handle(String cityName);
}
