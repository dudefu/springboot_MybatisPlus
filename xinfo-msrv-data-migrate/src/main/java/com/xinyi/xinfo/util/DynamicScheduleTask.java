package com.xinyi.xinfo.util;

import com.xinyi.xinfo.contant.Constant;
import com.xinyi.xinfo.domain.service.TaskExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 动态定时任务，循环查询数据库时间，执行任务
 */
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class DynamicScheduleTask implements SchedulingConfigurer {

    @Autowired
    private TaskExecuteService taskExecuteService ;

    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                //() -> System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime()),
              ()-> taskExecuteService.batchExecuteDatax(),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从配置文件中获取执行周期
                    String cron = Constant.cronTime;
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    System.out.println("cronTime:----------->>>>>>"+cron);
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }

}
