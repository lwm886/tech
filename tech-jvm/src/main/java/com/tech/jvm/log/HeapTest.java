package com.tech.jvm.log;

import java.util.ArrayList;
import java.util.List;

/**
 * Parallel GC
 *-Xloggc:./gc-%t.log
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps
 * -XX:+PrintGCTimeStamps
 * -XX:+PrintGCCause
 * -XX:+UseGCLogFileRotation
 * -XX:NumberOfGCLogFiles=10
 * -XX:GCLogFileSize=100M
 *
 * CMS GC:
 * -Xloggc:./gc-cms-%t.log
 * -Xms50M
 * -Xmx50M
 * -XX:MetaspaceSize=256M
 * -XX:MaxMetaspaceSize=256M
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps
 * -XX:+PrintGCTimeStamps
 * -XX:+PrintGCCause
 * -XX:+UseGCLogFileRotation
 * -XX:NumberOfGCLogFiles=10
 * -XX:GCLogFileSize=100M
 * -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC
 *
 * G1 GC:
 *
 * @author lw
 * @since 2022/7/28
 */
public class HeapTest {

    public static void main(String[] args) throws InterruptedException {
        List<HeapTest> heapTests=new ArrayList<>();
        while (true){
            heapTests.add(new HeapTest());
        }
    }

}
