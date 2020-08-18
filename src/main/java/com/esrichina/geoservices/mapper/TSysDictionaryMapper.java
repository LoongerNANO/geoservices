package com.esrichina.geoservices.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.esrichina.geoservices.entity.TSysDictionaryEntity;
/**
 * <ul>
 * <li>name:  TSysDictionaryMapper</li>
 * <li>author name: LOONGER CHEN</li>
 * <li>create time: 2020-08-17 16:05:06</li>
 * </ul>
 */ 
@Mapper
@Repository
public interface TSysDictionaryMapper extends BaseMapper<TSysDictionaryEntity> {

}

