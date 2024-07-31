package com.tech.sj.basic.algorithm.standard;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * 配置standard分片策略的范围分表算法
 * @author lw
 * @since 2024/7/31
 */
@Slf4j
public class RangeAlgorithmTableConfig implements RangeShardingAlgorithm<Long> {


    @Override
    public Collection<String> doSharding(Collection<String> availableTargetName, RangeShardingValue<Long> shardingValue) {
        //select * from course where cid between 1 and 100;
        Long upperEndpoint = shardingValue.getValueRange().upperEndpoint(); //100
        Long lowerEndpoint = shardingValue.getValueRange().lowerEndpoint(); //1

        String logicTableName = shardingValue.getLogicTableName();
        return Arrays.asList(logicTableName+"_1",logicTableName+"_2");
    }
}
