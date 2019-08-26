package com.xinyi.xinfo.util;

import java.io.File;
import java.io.FilenameFilter;

public class GetFileName {

    //获取文件夹下所有 json 文件名
    public static String[] getjsonFileName(String path) {
        File file = new File(path);
        String[] fileName = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.endsWith(".json")) {
                    return true;
                }
                return false;
            }
        });
        return fileName;
    }
}
