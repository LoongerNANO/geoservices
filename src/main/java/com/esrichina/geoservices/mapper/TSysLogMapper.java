package com.esrichina.geoservices.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.esrichina.geoservices.entity.TSysLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <ul>
 * <li>name:  TSysLogMapper</li>
 * <li>author name: LOONGER CHEN</li>
 * <li>create time: 2020-07-20 10:23:39</li>
 * </ul>
 */ 
@Mapper
@Repository
public interface TSysLogMapper extends BaseMapper<TSysLogEntity> {

}
