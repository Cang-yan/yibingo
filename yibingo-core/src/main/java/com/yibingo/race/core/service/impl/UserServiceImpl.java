package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.UserDao;
import com.yibingo.race.dal.entity.User;
import com.yibingo.race.core.service.UserService;
import com.yibingo.race.dal.filterMapper.UserFilterMapper;



@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {


}