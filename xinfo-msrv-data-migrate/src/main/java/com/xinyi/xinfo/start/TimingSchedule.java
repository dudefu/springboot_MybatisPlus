package com.xinyi.xinfo.start;

import com.xinyi.xinfo.contant.Constant;
import com.xinyi.xinfo.domain.model.TaskDescription;
import com.xinyi.xinfo.domain.repository.TaskDescriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilenameFilter;

@Service
public class TimingSchedule {

    @Autowired
    private  TaskDescriptionMapper taskDescriptionMapper;


    public static void main(String[] args) {
        //定时任务
//        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//        service.scheduleAtFixedRate(new Runnable(){
//
//            @Override
//            public void run() {
//                batchExecuteDatax();
//            }
//
//        }, Constant.initDelay, Constant.oneDay, TimeUnit.MILLISECONDS);

        new TimingSchedule().batchExecuteDatax();

    }
    //批量执行datax
    public  void batchExecuteDatax(){
        try {
            System.out.println("------------------startJob----------------------");
            String[] str = getFileName(Constant.jsonPath);
            for (String name : str) {  //解决阻塞问题 "python -loglevel quiet "+Contant.dataxPath+" "+Contant.jsonPath+"/"+name+" --jobid="+jobId
                String tdTaskId = name.substring(name.indexOf(".")-1,name.indexOf("."));
                TaskDescription taskDescription = taskDescriptionMapper.queryTaskDescriptionById(Integer.parseInt(tdTaskId));
                int jobjsonstate = taskDescription.getTdJobjsonstate();
                System.out.println(jobjsonstate);
                String executeCmd = "python "+ Constant.dataxPath+" "+ Constant.jsonPath+"/"+name+" --jobid="+tdTaskId;
                System.out.println(executeCmd);
//                Process pr = Runtime.getRuntime().exec(executeCmd);
//                BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//                String line = null;
//                while ((line = in.readLine()) != null) {
//                    System.out.println(line);
//                }
//                in.close();
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
