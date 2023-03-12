package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.FormLabelDao;
import com.yibingo.race.dal.entity.FormLabel;
import com.yibingo.race.core.service.FormLabelService;
import com.yibingo.race.dal.filterMapper.FormLabelFilterMapper;



@Service("formLabelService")
public class FormLabelServiceImpl extends ServiceImpl<FormLabelDao, FormLabel> implements FormLabelService {


}