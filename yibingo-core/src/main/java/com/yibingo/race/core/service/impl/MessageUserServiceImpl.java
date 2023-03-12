package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.MessageUserDao;
import com.yibingo.race.dal.entity.MessageUser;
import com.yibingo.race.core.service.MessageUserService;
import com.yibingo.race.dal.filterMapper.MessageUserFilterMapper;



@Service("messageUserService")
public class MessageUserServiceImpl extends ServiceImpl<MessageUserDao, MessageUser> implements MessageUserService {


}