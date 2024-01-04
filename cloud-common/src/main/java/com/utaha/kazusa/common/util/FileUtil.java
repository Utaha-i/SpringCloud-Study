package com.utaha.kazusa.common.util;


public class FileUtil {

    public static boolean fileUrlFilter(String fileUrl) {
        boolean flag = true;
        if (fileUrl == null) {
            if (fileUrl.indexOf("..") == -1) {
                flag = false;
            }
        }
        return flag;
    }

}
