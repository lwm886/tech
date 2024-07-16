package com.tech.mongo.test;

import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * @author lw
 * @since 2024/7/16
 */
@Component
@Slf4j
public class ApplicationRunnerTest implements ApplicationRunner {
    
    @Resource
    MongoTemplate mongoTemplate;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        //插入文档
        Person joy = new Person("joy", 34);
        Person insert = mongoTemplate.insert(joy);
        log.info("insert:{}",insert);
        
        //查询文档
        Person find = mongoTemplate.findById(joy.getId(), Person.class);
        log.info("found:{}",find);
        
        //更新文档
         mongoTemplate.updateFirst(query(where("name").is("joy")), update("age", 35), Person.class);
        Person one = mongoTemplate.findOne(query(where("name").is("joy")), Person.class);
        log.info("updated:{}",one);
        
        //删除文档
        mongoTemplate.remove(one);

        List<Person> all = mongoTemplate.findAll(Person.class);
        log.info("Number of people = {}",all.size());
        mongoTemplate.dropCollection(Person.class);

    }
}
