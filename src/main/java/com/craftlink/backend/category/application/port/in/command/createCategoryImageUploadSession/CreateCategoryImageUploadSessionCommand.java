package com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession;

public record CreateCategoryImageUploadSessionCommand(String fileName,
                                                      String contentType,
                                                      Long fileSize) {

}
