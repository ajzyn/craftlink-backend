package com.craftlink.backend.category.application.port.in.query.getServiceDetails;

public interface GetServiceDetailsUseCase {

  ServiceDetailsView handle(String slug);
}
