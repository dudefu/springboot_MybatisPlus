package com.xinyi.xinfo.domain.service.impl;

import com.xinyi.xinfo.contant.Constant;
import com.xinyi.xinfo.domain.model.TaskDescription;
import com.xinyi.xinfo.domain.repository.TaskDescriptionMapper;
import com.xinyi.xinfo.domain.service.TaskExecuteService;
import com.xinyi.xinfo.util.GetFileName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskExecuteServiceImpl implements TaskExecuteService {

    @Autowired
    private TaskDescriptionMapper taskDescriptionMapper;

    @Override
    public int batchExecuteDatax() {
        try {
            System.out.println("------------------startJob----------------------");
            String[] str = GetFileName.getjsonFileName(Constant.jsonPath);
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
        return 0;
    }

    @Override
    public int SingleExecuteDatax(int tdTaskId) {
        return 0;
    }
}
