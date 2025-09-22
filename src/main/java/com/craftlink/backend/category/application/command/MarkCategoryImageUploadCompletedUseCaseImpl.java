package com.craftlink.backend.category.application.command;

import com.craftlink.backend.category.application.port.in.command.markCategoryImageCompleted.MarkCategoryImageUploadCompletedCommand;
import com.craftlink.backend.category.application.port.in.command.markCategoryImageCompleted.MarkCategoryImageUploadCompletedUseCase;
import com.craftlink.backend.category.application.port.out.write.CategoryImageRepository;
import com.craftlink.backend.category.domain.model.categoryImage.CategoryImage;
import com.craftlink.backend.category.domain.model.categoryImage.vo.CategoryImageId;
import com.craftlink.backend.infrastructure.exceptions.custom.BusinessException;
import com.craftlink.backend.infrastructure.exceptions.enums.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarkCategoryImageUploadCompletedUseCaseImpl implements MarkCategoryImageUploadCompletedUseCase {

  private final CategoryImageRepository categoryImageRepository;

  @Override
  public void handle(MarkCategoryImageUploadCompletedCommand cmd) {
    var id = new CategoryImageId(cmd.id());

    var categoryImage = categoryImageRepository.findById(id).orElseThrow(() -> new BusinessException(
        ExceptionCode.FAILED_COMPLETE_UPLOADING));

    CategoryImage completedImage = categoryImage.markAsCompleted();

    categoryImageRepository.save(completedImage);
  }
}
