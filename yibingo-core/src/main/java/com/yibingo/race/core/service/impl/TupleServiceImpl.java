package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.TupleDao;
import com.yibingo.race.dal.entity.Tuple;
import com.yibingo.race.core.service.TupleService;
import com.yibingo.race.dal.filterMapper.TupleFilterMapper;



@Service("tupleService")
public class TupleServiceImpl extends ServiceImpl<TupleDao, Tuple> implements TupleService {


}