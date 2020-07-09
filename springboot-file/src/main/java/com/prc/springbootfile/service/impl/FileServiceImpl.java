package com.prc.springbootfile.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.prc.springbootfile.service.FileService;
import com.prc.springbootfile.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {

    //文件上传文件夹的名称
    @Value("${file.path}")
    private String filePath;

    public static final String DATE_TYPE_SLASH = "yyyy" + File.separator + "MM" + File.separator + "dd";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //单文件上传
    @Override
    public String upload(MultipartFile file, String folder) throws IOException {
        logger.info("单文件上传:" + folder);
        //上传文件大小
        long filesize = file.getSize();

        //文件上传路径
        String datePath = new SimpleDateFormat(DATE_TYPE_SLASH).format(new Date());
        String coursePath = System.getProperty("user.dir") + File.separator + filePath + File.separator + folder + File.separator + datePath + File.separator;
        //格式化路径
        Map<String,Object> param = FileUtils.formatPath(coursePath);
        String downloadPath = (String) param.get("downloadPath");
        String path = (String) param.get("path");
        //判断上传文件夹是否存在,不存在则创建
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        //开始上传文件
        Map<String,Object> data = new HashMap<>();
        //获取文件名加后缀
        String fileName = file.getOriginalFilename();
        logger.info("文件名加后缀:" + fileName);
        if(fileName!=null&&fileName!=""){
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            logger.info("文件后缀:" + fileF);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String Name=sdf.format(date)+"_" + new Random().nextInt(1000) + fileF;//新的文件名
            logger.info("修改后的文件名:" + Name);

            File dest = new File(downloadPath + Name);
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
                dest.createNewFile();
            }
            try{
                file.transferTo(dest);
                data.put("filePath",downloadPath + Name);
                data.put("fileName", Name);
                data.put("data","success");
            }catch (IOException e){
                e.printStackTrace();
                data.put("data","error");
            }
        }
        logger.info("返回数据:" + JSONObject.toJSONString(data));
        return JSONObject.toJSONString(data);
    }

    //多文件上传
    @Override
    public String uploadMore(MultipartFile[] files, String folder) throws IOException {
        logger.info("多文件上传:" + folder);

        Map<String,Object> map = new HashMap<>();
        List<String> filePaths = new ArrayList<>();
        List<String> fileNames = new ArrayList<>();

        for (MultipartFile file : files) {
            long filesize = file.getSize();//文件大小

            String datePath = new SimpleDateFormat(DATE_TYPE_SLASH).format(new Date());
            String coursePath = System.getProperty("user.dir") + File.separator + filePath + File.separator + folder + File.separator + datePath + File.separator;
            //格式化路径
            Map<String,Object> param = FileUtils.formatPath(coursePath);
            String downloadPath = (String) param.get("downloadPath");
            String path = (String) param.get("path");

            //判断上传文件夹是否存在,不存在则创建
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            //开始上传文件
            Map<String,Object> data = new HashMap<>();

            String fileName=file.getOriginalFilename();//获取文件名加后缀
            System.out.println("获取文件名加后缀:"+fileName);
            if(fileName!=null&&fileName!=""){
                String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String Name=sdf.format(date)+"_"+new Random().nextInt(1000)+fileF;//新的文件名
                logger.info("修改后的文件名:" + Name);

                File dest = new File(downloadPath + Name);
                if(!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                    dest.createNewFile();
                }
                try{
                    file.transferTo(dest);
                    filePaths.add(downloadPath + Name);
                    fileNames.add(Name);
                }catch (IOException e){
                    e.printStackTrace();
                    map.put("data","error");
                    return JSONObject.toJSONString(map);
                }
            }
        }
        map.put("filePaths",filePaths);
        map.put("fileName", fileNames);
        map.put("data","success");
        return JSONObject.toJSONString(map);
    }

    //文件删除
    @Override
    public String deleteFile(String filePath) throws IOException {
        logger.info("文件删除");
        Map<String, Object> map = new HashMap<>();
        logger.info("文件路径:"+filePath);
        if (filePath != null && !"".equals(filePath)) {
            // 删除单个文件
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                file.delete();
                map.put("message", "success");
            } else {
                map.put("message", "error");
            }
        } else {
            map.put("message", "error");
        }
        return JSONObject.toJSONString(map);
    }


}
