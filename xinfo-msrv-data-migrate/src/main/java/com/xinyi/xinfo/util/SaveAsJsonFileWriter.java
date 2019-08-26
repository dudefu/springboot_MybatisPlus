package com.xinyi.xinfo.util;

import com.xinyi.xinfo.contant.Constant;

import java.io.FileWriter;
import java.io.IOException;

public class SaveAsJsonFileWriter {

    public static void main(String[] args) {
        String name = "Job-20190822171530-3.json";
        String tdTaskId = name.substring(name.indexOf(".")-1,name.indexOf("."));
        System.out.println(tdTaskId);
        System.out.println(Constant.jsonPath+"/"+name);
        saveAsFileWriter("123",name);
    }
    public static void saveAsFileWriter(String content,String FileName) {
        FileWriter fwriter = null;
        try {
            // fwriter = new FileWriter(filePath,true);
            // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
            fwriter = new FileWriter(Constant.jsonPath+"/"+FileName);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
