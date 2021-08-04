package com.codefans.template.web.controller;

import com.codefans.template.domain.CommonResult;
import com.codefans.template.scheduler.DynamicJobFactory;
import org.quartz.JobDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Author: codefans
 * @Date: 2020-08-04 8:30
 * Spring+quartz实现动态化定时任务
 * https://my.oschina.net/u/1760791/blog/887956
 *
 */
@Controller
@RequestMapping("scheduler")
public class SchedulerController {

    /**
     * log
     */
    private Logger log = LoggerFactory.getLogger(SchedulerController.class);

    @Resource
    private DynamicJobFactory dynamicJobFactory;

    String className = "com.codefans.template.scheduler.MyJob";
    String cronExpression = "*/2 * * * * ?";


    /**
     * 添加一个新任务
     * @return
     */
    @RequestMapping(value = "/add")
    public CommonResult add(){
        CommonResult result = new CommonResult();
        try {

            dynamicJobFactory.addJob(className,cronExpression);
            result.setMessage("添加动态任务成功！");
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("添加任务出现异常");
        }
        return result;
    }

    /**
     * 暂停任务
     * @return
     */
    @RequestMapping(value="/pause")
    public CommonResult pause(){
        CommonResult result = new CommonResult();
        try {
            dynamicJobFactory.pause(className);
            result.setMessage("暂停任务成功！");
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("暂停任务出现异常");
        }
        return result;
    }

    /**
     * 重启任务
     * @return
     */
    @RequestMapping(value="/resume")
    public CommonResult resume(){
        CommonResult result = new CommonResult();
        try {
            dynamicJobFactory.resume(className);
            result.setMessage("重启任务成功！");
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("重启任务出现异常");
        }
        return result;
    }

    /**
     * 停止任务
     * @return
     */
    @RequestMapping(value = "/stop")
    public CommonResult stop(){
        CommonResult result = new CommonResult();
        try{
            dynamicJobFactory.stop(className);
            result.setMessage("任务停止成功");
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("任务停止失败");
        }
        return result;
    }

    /**
     * 修改任务执行时间
     * @return
     */
    @RequestMapping(value = "/update")
    public CommonResult update(String cronExpression){
        CommonResult result = new CommonResult();
        try {
            dynamicJobFactory.updateJobTime(className,cronExpression);
            result.setMessage("更新任务执行时间成功");
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("更新任务执行时间失败");
        }
        return result;
    }

    /**
     * 查看任务状态
     * @return
     */
    @RequestMapping(value = "/detail")
    public CommonResult getJobDetail(){
        CommonResult result = new CommonResult();
        JobDetail jobDetail;
        try {
            jobDetail = dynamicJobFactory.getJobDetail(className);
            result.setMessage("获取任务信息成功");
            result.setSuccess(true);
            if(null != jobDetail){
                //JobDetail中的JobBuilder是不能序列化的。因此不能放JobDetail
                result.setData(jobDetail.getDescription());
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("获消息异常");
        }
        return result;
    }


}
