package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.FormCountDao;
import com.yibingo.race.dal.entity.FormCount;
import com.yibingo.race.core.service.FormCountService;
import com.yibingo.race.dal.filterMapper.FormCountFilterMapper;



@Service("formCountService")
public class FormCountServiceImpl extends ServiceImpl<FormCountDao, FormCount> implements FormCountService {


}