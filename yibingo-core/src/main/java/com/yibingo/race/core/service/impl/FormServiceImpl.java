package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.FormDao;
import com.yibingo.race.dal.entity.Form;
import com.yibingo.race.core.service.FormService;
import com.yibingo.race.dal.filterMapper.FormFilterMapper;



@Service("formService")
public class FormServiceImpl extends ServiceImpl<FormDao, Form> implements FormService {


}