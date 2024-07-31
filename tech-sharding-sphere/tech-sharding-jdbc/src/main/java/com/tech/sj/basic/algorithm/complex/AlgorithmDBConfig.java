package com.tech.sj.basic.algorithm.complex;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * complex 复杂分库策略配置类
 * @author lw
 * @since 2024/7/31
 */
public class AlgorithmDBConfig implements ComplexKeysShardingAlgorithm<Long> {
    //    SELECT  cid,cname,user_id,cstatus  FROM course
//    WHERE  cid BETWEEN ? AND ? AND user_id = ?
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> shardingValue) {
        Range<Long> cidRange = shardingValue.getColumnNameAndRangeValuesMap().get("cid"); //范围查询
        Collection<Long> userIdCol = shardingValue.getColumnNameAndShardingValuesMap().get("user_id"); //精确值查询
        Long upper = cidRange.upperEndpoint();
        Long lower = cidRange.lowerEndpoint();

        List<String> list = new ArrayList<>();
        for (long userId:userIdCol){
            //m$->{cid%2+1}
            BigInteger userIdB = BigInteger.valueOf(userId);
            BigInteger target = (userIdB.mod(new BigInteger("2"))).add(new BigInteger("1"));
            list.add("m"+target);
        }

        return list;
    }
}
