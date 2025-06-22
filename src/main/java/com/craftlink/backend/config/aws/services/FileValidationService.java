package com.craftlink.backend.config.aws.services;

import com.craftlink.backend.config.aws.properties.PresignedUrlArgs;
import com.craftlink.backend.config.exceptions.custom.ValidationException;
import com.craftlink.backend.config.exceptions.enums.ExceptionCode;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileValidationService {

    public void validateUploadRequest(PresignedUrlArgs presignedUrlArgs) {

        validateFileSize(presignedUrlArgs.getRequest().getFileSize(), presignedUrlArgs.getMaxSize());
        validateContentType(presignedUrlArgs.getRequest().getContentType(), presignedUrlArgs.getAllowedContentTypes());
        validateFileExtension(presignedUrlArgs.getRequest().getFileName(), presignedUrlArgs.getAllowedFileExtensions());
    }

    private void validateFileSize(Long fileSize, Long maxSize) {
        if (fileSize > maxSize) {
            throw new ValidationException(ExceptionCode.FILE_IS_TOO_BIG);
        }
    }

    private void validateContentType(String contentType, List<String> allowedContentTypes) {
        if (!allowedContentTypes.contains(contentType.toLowerCase())) {
            throw new ValidationException(ExceptionCode.CONTENT_TYPE_NOT_ALLOWED,
                Map.of("image", String.format("Please upload valid images with allowed content type: %s",
                    String.join(", ", allowedContentTypes))));
        }
    }

    private void validateFileExtension(String fileName, List<String> allowedFileExtensions) {
        String extension = getFileExtension(fileName).toLowerCase();

        if (!allowedFileExtensions.contains(extension)) {
            throw new ValidationException(ExceptionCode.EXTENSION_NOT_ALLOWED,
                Map.of("image",
                    String.format("Please use files with extensions: %s", String.join(", ", allowedFileExtensions))));
        }
    }

    private String getFileExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        return lastDot > 0 ? fileName.substring(lastDot + 1) : "";
    }
}
