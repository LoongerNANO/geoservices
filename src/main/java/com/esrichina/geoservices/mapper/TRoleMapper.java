package com.esrichina.geoservices.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.esrichina.geoservices.entity.TRoleEntity;
/**
 * <ul>
 * <li>name:  TRoleMapper</li>
 * <li>author name: LOONGER CHEN</li>
 * <li>create time: 2020-08-19 09:44:25</li>
 * </ul>
 */ 
@Mapper
@Repository
public interface TRoleMapper extends BaseMapper<TRoleEntity> {

}

