package com.tech.sj.basic.algorithm.hint;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Arrays;
import java.util.Collection;

/**
 * Hint强制路由分表策略配置
 * @author lw
 * @since 2024/7/31
 */
public class HintShardingAlgorithmTableConfig implements HintShardingAlgorithm<Integer> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Integer> shardingValue) {
        String key= shardingValue.getLogicTableName()+"_"+shardingValue.getValues().toArray()[0];
        if(availableTargetNames.contains(key)){
            return Arrays.asList(key);
        }
        throw new UnsupportedOperationException("route "+key+" is not supported ，please check your config");
    }
}
