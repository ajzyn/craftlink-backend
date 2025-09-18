package com.craftlink.backend.category.application.port.in.query.getCategoryDetails;

public interface GetCategoryDetailsUseCase {

  CategoryDetailsView handle(String slug);
}
