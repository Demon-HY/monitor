package com.monitor.baseservice.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件相关工具类
 *
 * Created by Administrator on 2017/7/1 0001.
 */
public class FileUtils {

    /**
     * get file type
     * @param fileName file name
     * @return file type, error return null
     */
    public static String getFileType(String fileName) {
        if (fileName != null ) {
            int i = fileName.lastIndexOf('.');
            if (i > -1) {
                return fileName.substring(i+1).toLowerCase();
            }
        }

        return null;
    }

    /**
     * write byte stream to file, if file path not found, be recursion create file path
     * @param fileBytes 写入的字节流
     * @param filePath 文件路径
     * @param fileName 文件名
     * @return true/false
     */
    public static boolean uploadFile(byte[] fileBytes, String filePath, String fileName) {
        File file = new File(filePath, fileName);
        boolean isFileExists = file.exists();
        if (!isFileExists) {
            isFileExists = file.mkdirs();
        }
        // 文件路径创建失败
        if (!isFileExists) {
            return false;
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(fileBytes);
            out.flush();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert  out != null;
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(getFileType("a.pdf.pdf"));

        // write file
    }
}
