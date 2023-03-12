package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.TupleMemberDao;
import com.yibingo.race.dal.entity.TupleMember;
import com.yibingo.race.core.service.TupleMemberService;
import com.yibingo.race.dal.filterMapper.TupleMemberFilterMapper;



@Service("tupleMemberService")
public class TupleMemberServiceImpl extends ServiceImpl<TupleMemberDao, TupleMember> implements TupleMemberService {


}