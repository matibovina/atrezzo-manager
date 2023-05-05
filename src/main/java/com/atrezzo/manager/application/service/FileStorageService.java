package com.atrezzo.manager.application.service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface FileStorageService {

    String storeFile(MultipartFile file);
    Resource loadFileAsResource(String fileName);

}
