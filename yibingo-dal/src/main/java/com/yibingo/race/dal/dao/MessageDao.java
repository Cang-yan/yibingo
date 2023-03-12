package com.yibingo.race.dal.dao;

import com.yibingo.race.dal.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息模板
 * 
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Mapper
public interface MessageDao extends BaseMapper<Message> {
	
}
