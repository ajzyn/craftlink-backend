package com.craftlink.backend.category.application.command;

import com.craftlink.backend.category.adapter.config.CategoryImageUploadProperties;
import com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession.CreateCategoryImageUploadSessionCommand;
import com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession.CreateCategoryImageUploadSessionResult;
import com.craftlink.backend.category.application.port.in.command.createCategoryImageUploadSession.CreateCategoryImageUploadSessionUseCase;
import com.craftlink.backend.category.application.port.out.write.CategoryImageRepository;
import com.craftlink.backend.category.domain.model.categoryImage.CategoryImage;
import com.craftlink.backend.category.domain.model.categoryImage.vo.ContentType;
import com.craftlink.backend.category.domain.model.categoryImage.vo.FileName;
import com.craftlink.backend.category.domain.model.categoryImage.vo.FileSize;
import com.craftlink.backend.category.domain.model.categoryImage.vo.ImageKey;
import com.craftlink.backend.shared.security.CurrentUserProvider;
import com.craftlink.backend.shared.storage.FileIdentityPort;
import com.craftlink.backend.shared.storage.FileStoragePort;
import com.craftlink.backend.shared.storage.PresignedUrlRequest;
import com.craftlink.backend.shared.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryImageUploadSessionUseCaseImpl implements CreateCategoryImageUploadSessionUseCase {

  private final FileStoragePort fileStorage;
  private final FileIdentityPort fileIdentity;
  private final CategoryImageRepository categoryImageRepository;
  private final CategoryImageUploadProperties categoryImageUploadProperties;
  private final CurrentUserProvider currentUserProvider;

  @Override
  public CreateCategoryImageUploadSessionResult handle(CreateCategoryImageUploadSessionCommand cmd) {
    var imageKey = fileIdentity.generateKey(
        categoryImageUploadProperties.getPrefix(),
        cmd.fileName(),
        currentUserProvider.getCurrentUser().userId().toString()
    );

    var presigned = fileStorage.generatePresignedUploadUrl(
        new PresignedUrlRequest(imageKey, cmd.contentType(), cmd.fileSize(), cmd.fileName())
    );

    var categoryImage = CategoryImage.create(
        new ImageKey(presigned.imageKey()),
        new FileName(cmd.fileName()),
        new FileSize(cmd.fileSize()),
        new UserId(currentUserProvider.getCurrentUser().userId()),
        new ContentType(cmd.contentType()),
        categoryImageUploadProperties.getAllowedFileExtensions(),
        categoryImageUploadProperties.getAllowedContentTypes(),
        categoryImageUploadProperties.getMaxSizeBytes()
    );

    categoryImageRepository.save(categoryImage);

    return new CreateCategoryImageUploadSessionResult(
        presigned.presignedUrl(),
        presigned.imageKey(),
        presigned.expiresAt()
    );
  }
}
