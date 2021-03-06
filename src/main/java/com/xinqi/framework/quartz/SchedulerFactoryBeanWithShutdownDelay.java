package com.xinqi.framework.quartz;

import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by IntelliJ IDEA.
 * User: xinqi
 * Date: 2017/3/23
 * Time: 17:01
 */
public class SchedulerFactoryBeanWithShutdownDelay  extends SchedulerFactoryBean {
    /**
     * 关于Quartz内存泄漏的不太美观的解决方案：
     * 在调用scheduler.shutdown(true)后增加延时，等待worker线程结束。
     */
    @Override
    public void destroy() throws SchedulerException {
        super.destroy();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
