package com.jvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 初始JVM配置  对象年龄动态判断机制导致对象不断的进入老年代
 * -Xms1536M -Xmx1536M -Xmn512M -Xss256K -XX:SurvivorRatio=6  -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=256M
 * -XX:+UseParNewGC  -XX:+UseConcMarkSweepGC  -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly
 *
 *  调整JVM配置  增大新生代内存，尽量避免对象年龄动态判断机制，
 *  但是老年代空间太小 引发了老年代空间分配担保机制 FullGC次数比Young GC还多，YGC前进行一次FGC，然后YGC，
 *  在YGC后老年代空间不足以存放存活对象又进行FGC，导致可能一次YGC进行两次FGC
 *  -Xms1536M -Xmx1536M -Xmn1024M -Xss256K -XX:SurvivorRatio=6  -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=256M
 * -XX:+UseParNewGC  -XX:+UseConcMarkSweepGC  -XX:CMSInitiatingOccupancyFraction=92 -XX:+UseCMSInitiatingOccupancyOnly
 *
 * YGC和FGC依旧很频繁，有大量对象进入老年代，用jmap找出占用内存较多的对象，如果不好定位程序，一般创建对象较多的程序CPU占用较高，
 * 可以用jstack jvisualvm定位cpu占用较高的程序，一次请求创建500M user对象，不合理，优化程序，减少一次请求对象创建的数量如将5000改为500
 */

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}