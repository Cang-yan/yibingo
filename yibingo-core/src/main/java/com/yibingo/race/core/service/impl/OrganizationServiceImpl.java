package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.OrganizationDao;
import com.yibingo.race.dal.entity.Organization;
import com.yibingo.race.core.service.OrganizationService;
import com.yibingo.race.dal.filterMapper.OrganizationFilterMapper;



@Service("organizationService")
public class OrganizationServiceImpl extends ServiceImpl<OrganizationDao, Organization> implements OrganizationService {


}