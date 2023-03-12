package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.FormRecordsDao;
import com.yibingo.race.dal.entity.FormRecords;
import com.yibingo.race.core.service.FormRecordsService;
import com.yibingo.race.dal.filterMapper.FormRecordsFilterMapper;



@Service("formRecordsService")
public class FormRecordsServiceImpl extends ServiceImpl<FormRecordsDao, FormRecords> implements FormRecordsService {


}