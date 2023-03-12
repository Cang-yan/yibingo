package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.MessageDao;
import com.yibingo.race.dal.entity.Message;
import com.yibingo.race.core.service.MessageService;
import com.yibingo.race.dal.filterMapper.MessageFilterMapper;



@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements MessageService {


}