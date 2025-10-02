package com.yeabchanya.e_commerceApi.Util.FileUploadUtil;

import org.springframework.web.multipart.MultipartFile;

public class FileValidation {
    public static void validatePhotoFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        if (!file.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }
        if (file.getSize() > 2 * 1024 * 1024) { // 2MB max
            throw new IllegalArgumentException("File size exceeds limit");
        }
    }
}
