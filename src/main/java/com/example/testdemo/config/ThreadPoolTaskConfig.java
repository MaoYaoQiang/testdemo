package com.example.testdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolTaskConfig {
   //核心线程数
   private static final Integer CORE_PO0L_SIZE=10;
   //最大线程数
   private static final Integer MAX_POOL_SIZE=10;
   //允许线城空闲时间,默认是秒
   private static final Integer KEEP_ALIVE_TIME=60;
   //缓冲队列大小
   private static final Integer QUEUE_CAPACITY=10;
   //等待的时间
    private static final Integer AWAITFOR_TERIMINATION=60;
   //默认线城池名字前缀
   private static final String THREAD_NAME_PREFIX="MAO-TEST";
   @Bean("taskExecutor")
   public ThreadPoolTaskExecutor taskExecutor(){
       ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
       threadPoolTaskExecutor.setCorePoolSize(CORE_PO0L_SIZE);
       threadPoolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
       threadPoolTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
       threadPoolTaskExecutor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
       threadPoolTaskExecutor.setThreadNamePrefix(THREAD_NAME_PREFIX);
       threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
       threadPoolTaskExecutor.setAwaitTerminationSeconds(AWAITFOR_TERIMINATION);
       return threadPoolTaskExecutor;
   }

}
