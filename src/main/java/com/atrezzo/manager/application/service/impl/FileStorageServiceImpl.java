package com.atrezzo.manager.application.service.impl;

import com.atrezzo.manager.application.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageServiceImpl implements FileStorageService {


    private final Path fileStorageLocation;

    public FileStorageServiceImpl() {
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory." + e.getMessage());
        }    }

    @Override
    public String storeFile(MultipartFile file, String dir, Long id) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file has a png or jpg extension
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!fileExtension.equals("png") && !fileExtension.equals("jpg")) {
            throw new RuntimeException("Only PNG and JPG images are allowed.");
        }
        String newFileName = "id_" + id + "_profile_picture." + fileExtension;

        try {
            if (newFileName.contains("..")) {
                throw new RuntimeException("Invalid file path.");
            }

            // Create subdirectory if it does not exist
            Path targetDirectory = this.fileStorageLocation.resolve(dir);
            if (!Files.exists(targetDirectory)) {
                Files.createDirectories(targetDirectory);
            }

            // Store the file in the subdirectory
            Path targetLocation = this.fileStorageLocation.resolve(Paths.get(dir, newFileName));
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return dir + "/" + newFileName;
        } catch (IOException e) {
            throw new RuntimeException("Could not store file." + e.getMessage());
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName, String dir) {
        try {
            Path filePath = this.fileStorageLocation.resolve(Paths.get(fileName)).normalize();

            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found.");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("File not found."+ e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }
