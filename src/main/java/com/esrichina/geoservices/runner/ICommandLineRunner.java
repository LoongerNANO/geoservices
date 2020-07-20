package com.esrichina.geoservices.runner;

import cn.hutool.json.JSONUtil;
import com.esrichina.geoservices.constant.ApplicationConstant;
import com.esrichina.geoservices.entity.TSysDictionaryEntity;
import com.esrichina.geoservices.mapper.TSysDictionaryMapper;
import com.esrichina.geoservices.redis.Redis;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
@Log4j2
public class ICommandLineRunner implements CommandLineRunner {

    @Resource
    private Redis redis;

    @Autowired
    private TSysDictionaryMapper tSysDictionaryMapper;

    @Override
    public void run(String... args) throws Exception {

        try {
            List<TSysDictionaryEntity> dictionaryList = tSysDictionaryMapper.selectList(null);
            //Map<String, List<TSysDictionaryEntity>> dictionaryMap = dictionaryList.stream().collect(Collectors.groupingBy(TSysDictionaryEntity::getIdentify));

            log.info("CACHE DICTIONARY IN REDIS");
            // 缓存字典
            redis.set(ApplicationConstant.BASE_DICTIONARY.getName(), JSONUtil.parse(dictionaryList));

        } catch (Exception e) {
            e.printStackTrace();
            log.error("FRAMEWORK START IS EXCEPTION OCCURRED, PLEASE INSPECTION REASON ...");
        } finally {
            log.info("PLEASE VISIT THE PAGE OF API ...");
        }
    }
}
