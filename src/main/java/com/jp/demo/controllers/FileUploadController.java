package com.jp.demo.controllers;



import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jp.demo.responses.FileUploadResponse;
import com.jp.demo.responses.UploadResponse;
import com.jp.demo.services.FileStorageService;


@RestController
public class FileUploadController {

  private final FileStorageService fileStorageService;

  public FileUploadController(FileStorageService fileStorageService) {
    this.fileStorageService = fileStorageService;
  }

  @PostMapping("/upload")
  public ResponseEntity<UploadResponse> uploadFile(
      @RequestParam(name = "file", required = false) MultipartFile file,
      @RequestParam("fullName") String fullName,
      @RequestParam("dateOfBirth") String dateOfBirth
  ) {
      String fileName = fileStorageService.storeFile(file);

      UploadResponse uploadResponse = new UploadResponse(fileName, fullName, dateOfBirth);
   
      return ResponseEntity.ok().body(uploadResponse);
  }
  
  
 

}
