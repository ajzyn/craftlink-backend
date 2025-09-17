package com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession;

public interface CreateCategoryImageUploadSessionUseCase {

  CreateCategoryImageUploadSessionResult handle(CreateCategoryImageUploadSessionCommand cmd);
}
