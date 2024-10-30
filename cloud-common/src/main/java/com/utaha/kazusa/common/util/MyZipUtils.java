//package com.utaha.kazusa.common.util;
//
//import cn.hutool.core.util.CharsetUtil;
//import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
//import net.lingala.zip4j.ZipFile;
//import net.lingala.zip4j.exception.ZipException;
//import net.lingala.zip4j.model.FileHeader;
//
//import java.io.File;
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.Charset;
//import java.util.*;
//
//
//public class MyZipUtils {
//
//    /**
//     * 解压zip包返回所有文件
//     *
//     * @param filePath  路径
//     * @param unZipList 已解压列表
//     * @return List<File>
//     * @throws ZipException
//     */
//    public static List<File> scanAndUnzipFile(String filePath, Set<String> unZipList) throws ZipException {
//        File file = new File(filePath);
//        List<File> fileList = Arrays.asList(file.listFiles());
//        List<File> fileListNew = new ArrayList<>();
//        if (CollectionUtils.isNotEmpty(fileList)) {
//            for (File file1 : fileList) {
//                if (file1.getName().indexOf(".zip") != -1) {
//                    if (unZipList.contains(file1.getName())) {
//                        continue;
//                    }
//                    unZipList.add(file1.getName());
//                    ZipFile zipFile1 = new ZipFile(file1);
//                    extractAll(filePath, zipFile1);
//                    return scanAndUnzipFile(filePath, unZipList);
//                }
//                fileListNew.add(file1);
//            }
//            return fileListNew;
//        }
//        return null;
//    }
//
//    public static String extractAll(String filePath, ZipFile zip) {
//        zip.setCharset(Charset.forName("utf-8"));
//        System.out.println("begin unpack zip file....");
//
//        try {
//            zip.getFileHeaders().forEach(v -> {
//                String extractedFile = getFileName(v);
//                try {
//                    zip.extractFile(v, filePath, extractedFile);
//                } catch (ZipException e) {
//                    System.out.println("解压失败 ：" + extractedFile);
//                    e.printStackTrace();
//                    return;
//                }
//                System.out.println("解压成功 ：" + extractedFile);
//            });
//        } catch (ZipException e) {
//            e.printStackTrace();
//        }
//        System.out.println("unpack zip file success");
//        return "success";
//    }
//
//    public static String getFileName(FileHeader fileHeader) {
//
//        try {
//            // 目前压缩包主要是两种来源WINdows和Linux
//            if (fileHeader.isFileNameUTF8Encoded()) {
//                return new String(fileHeader.getFileName().getBytes("Cp437"), CharsetUtil.CHARSET_UTF_8.name());
//            } else {
//                return new String(fileHeader.getFileName().getBytes("Cp437"), CharsetUtil.CHARSET_GBK.name());
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return fileHeader.getFileName();
//    }
//}
//
//
