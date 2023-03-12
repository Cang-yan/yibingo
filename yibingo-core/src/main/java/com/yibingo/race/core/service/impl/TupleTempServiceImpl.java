package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.TupleTempDao;
import com.yibingo.race.dal.entity.TupleTemp;
import com.yibingo.race.core.service.TupleTempService;
import com.yibingo.race.dal.filterMapper.TupleTempFilterMapper;



@Service("tupleTempService")
public class TupleTempServiceImpl extends ServiceImpl<TupleTempDao, TupleTemp> implements TupleTempService {


}