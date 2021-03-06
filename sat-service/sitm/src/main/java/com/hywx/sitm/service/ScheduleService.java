package com.hywx.sitm.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.hywx.sitm.global.GlobalAccess;
import com.hywx.sitm.quartz.CronSchedulerJob;
import com.hywx.sitm.redis.RedisFind;
import com.hywx.sitm.vo.TmRsltFrameVO;

/*
 * 1.InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候会执行该方法。
 * 2.ApplicationRunner和CommandLineRunner接口，当springboot的main方法快要执行结束时会调用afterRefresh然后再调用callRunners来加载所有的实现ApplicationRunner和CommandLineRunner的类然后执行run方法来初始化所写的内容。
 * 3.ApplicationContext初始化或刷新完成后触发的事件：ContextRefreshedEvent类型ApplicationListener接口ApplicationListener<ContextRefreshedEvent>,然后重写onApplicationEvent方法。
 * 执行顺序：（spring bean初始化） –> spring事件ContextRefreshedEvent–> CommandLineRunner/ApplicationRunner
 * 实现ApplicationRunner和CommandLineRunner接口（建议）
 */
@Service
@Order(2)
public class ScheduleService implements ApplicationRunner {
	@Autowired
    private CronSchedulerJob scheduleJobs;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<String> groupList = args.getOptionValues("group");
		if (groupList == null || groupList.isEmpty())
		    return;
		
		//遥测仿真定时任务
		scheduleJobs.scheduleJobs();
		
//		Executors.newSingleThreadScheduledExecutor()
//		         .scheduleAtFixedRate( ()-> { System.out.println("Task ScheduledExecutorService "); }, 
//		        		               0, 
//		        		               3, 
//		        		               TimeUnit.SECONDS);
		
		System.out.println("******************定时任务启动**************");
	}
	

}
