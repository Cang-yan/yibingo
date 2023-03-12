package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.VipDao;
import com.yibingo.race.dal.entity.Vip;
import com.yibingo.race.core.service.VipService;
import com.yibingo.race.dal.filterMapper.VipFilterMapper;



@Service("vipService")
public class VipServiceImpl extends ServiceImpl<VipDao, Vip> implements VipService {


}