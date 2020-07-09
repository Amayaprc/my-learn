package com.prc.springbootfile.controller;

import com.prc.springbootfile.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FileService fileService;

    //单文件上传
    @PostMapping(value="/upload",produces = "text/html;charset=UTF-8")
    public String file(@RequestParam(value = "file", required = false) MultipartFile file,
                       @RequestParam(value = "folder", defaultValue = "common") String folder) throws IOException {
        return fileService.upload(file,folder);
    }

    //多文件上传
    @RequestMapping("/uploadMore")
    public String uploadMore(@RequestParam(value = "files", required = false) MultipartFile[] files,
                             @RequestParam(value = "folder", defaultValue = "common") String folder) throws IOException {
        return fileService.uploadMore(files,folder);
    }

    //文件删除
    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestParam("filePath") String filePath) throws IOException {
        return fileService.deleteFile(filePath);
    }
}
