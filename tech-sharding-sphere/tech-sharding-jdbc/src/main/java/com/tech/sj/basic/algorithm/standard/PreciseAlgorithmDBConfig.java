package com.tech.sj.basic.algorithm.standard;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;

/**
 * 配置standard分片策略的精确分库算法
 * @author lw
 * @since 2024/7/31
 */
@Slf4j
public class PreciseAlgorithmDBConfig implements PreciseShardingAlgorithm<Long> {

    //select * from course where cid = ? or cid in (?,?)
    @Override
    public String doSharding(Collection<String> availableTargetName, PreciseShardingValue<Long> shardingValue) {
        //获取逻辑表名称、分片键的名称、分片键的值
        String logicTableName = shardingValue.getLogicTableName();
        String cid = shardingValue.getColumnName();
        Long cidValue = shardingValue.getValue();

        //实现 m_$->{cid%2+1)
        BigInteger shardingValueB = BigInteger.valueOf(cidValue);
        BigInteger resB = shardingValueB.mod(new BigInteger("2")).add(new BigInteger("1"));
        String key="m"+resB;
        if(availableTargetName.contains(key)){
            return key;
        }
        throw new UnsupportedOperationException("route "+key+" is not supported ，please check your config");
    }
}
