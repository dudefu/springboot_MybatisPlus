package com.xinyi.xinfo.start;

import com.xinyi.xinfo.contant.Constant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TimingSchedule {

    public static void main(String[] args) {
        //定时任务
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable(){

            @Override
            public void run() {
                executeDatax();
            }

        }, Constant.initDelay, Constant.oneDay, TimeUnit.MILLISECONDS);

    }
    //批量执行datax
    public static void executeDatax(){
        int jobId = 1 ;
        try {
            System.out.println("------------------startJob----------------------");
            String[] str = getFileName(Constant.jsonPath);
            for (String name : str) {  //解决阻塞问题 "python -loglevel quiet "+Contant.dataxPath+" "+Contant.jsonPath+"/"+name+" --jobid="+jobId
                String executeCmd = "python "+ Constant.dataxPath+" "+ Constant.jsonPath+"/"+name+" --jobid="+jobId;
                System.out.println(executeCmd);
                Process pr = Runtime.getRuntime().exec(executeCmd);
                BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line = null;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                in.close();
//                pr.waitFor();
            }
            System.out.println("----------------endJob------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取文件夹下所有 json 文件名
    public static String[] getFileName(String path) {
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
