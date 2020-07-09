package com.prc.springbootfile.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String upload(MultipartFile file, String folder) throws IOException;

    String uploadMore(MultipartFile[] files, String folder) throws IOException;

    String deleteFile(String filePath) throws IOException;
}
