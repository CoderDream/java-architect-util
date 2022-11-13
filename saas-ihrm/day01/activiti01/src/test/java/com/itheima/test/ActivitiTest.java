package com.itheima.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * 测试类
 *     作用：测试activiti所需要的25张表的生成
 */
public class ActivitiTest {

    @Test
    public void testGenTable(){
        //条件：1.activiti配置文件名称：activiti.cfg.xml   2.bean的id="processEngineConfiguration"
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }



    // HistoryService historyService = processEngine.getHistoryService();

   /** @Test
    public void testGenTable(){
        //1.创建ProcessEngineConfiguration对象  第一个参数:配置文件名称  第二个参数是processEngineConfiguration的bean的id
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml","processEngineConfiguration01");
        //2.创建ProcesEngine对象
        ProcessEngine processEngine = configuration.buildProcessEngine();

        //3.输出processEngine对象
        System.out.println(processEngine);
    }
    */
}
