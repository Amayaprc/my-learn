package com.prc.mypoi.service;

import java.io.InputStream;
import java.io.OutputStream;

public interface DeviceService {

    void exportExcel(String[] ids, OutputStream out);

    void importExcel(String fileName, InputStream in);
}
