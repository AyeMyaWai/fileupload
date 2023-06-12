package com.jp.demo.controllers;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jp.demo.upload.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;


import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Controller
public class FileDownloadController {
    
	 @GetMapping("/downloadFile/{fileCode}")
	    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
	        FileDownloadUtil downloadUtil = new FileDownloadUtil();
	         
	        Resource resource = null;
	        try {
	            resource = downloadUtil.getFileAsResource(fileCode);
	        } catch (IOException e) {
	            return ResponseEntity.internalServerError().build();
	        }
	         
	        if (resource == null) {
	            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
	        }
	         
	        String contentType = "application/octet-stream";
	        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
	         
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
	                .body(resource);       
	    }
	 


}