package com.itheima.day03.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

/**
 * 删除已经部署的流程定义
 *
 * 背后影响的表:
 * act_ge_bytearray
   act_re_deployment
   act_re_procdef
 */
public class DeleteProcessDefinition {

    /**
     * 注意事项：
     *     1.当我们正在执行的这一套流程没有完全审批结束的时候，此时如果要删除流程定义信息就会失败
     *     2.如果公司层面要强制删除,可以使用repositoryService.deleteDeployment("1",true);
     *     //参数true代表级联删除，此时就会先删除没有完成的流程结点，最后就可以删除流程定义信息  false的值代表不级联
     *
     * @param args
     */
    public static void main(String[] args) {
        //1.得到ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.创建RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3.执行删除流程定义  参数代表流程部署的id
        repositoryService.deleteDeployment("1");
    }
}
