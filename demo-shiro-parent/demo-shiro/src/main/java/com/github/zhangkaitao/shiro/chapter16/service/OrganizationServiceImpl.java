package com.github.zhangkaitao.shiro.chapter16.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.zhangkaitao.shiro.chapter16.dao.OrganizationMapper;
import com.github.zhangkaitao.shiro.chapter16.entity.Organization;
import com.github.zhangkaitao.shiro.chapter16.entity.OrganizationExample;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationDao;

    @Override
    public int createOrganization(Organization organization) {
        return organizationDao.insert(organization);
    }

    @Override
    public int updateOrganization(Organization organization) {
        return organizationDao.updateByPrimaryKey(organization);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        organizationDao.deleteByPrimaryKey(organizationId);
    }

    @Override
    public Organization findOne(Long organizationId) {
    	
        return organizationDao.selectByPrimaryKey(organizationId);
    }

    @Override
    public List<Organization> findAll() {
    	OrganizationExample example=new OrganizationExample();
        return organizationDao.selectByExample(example);
    }

    @Override
    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
    	OrganizationExample example=new OrganizationExample();
    	OrganizationExample.Criteria criteria= example.createCriteria();
        return organizationDao.selectByExample(example);
    }

    @Override
    public void move(Organization source, Organization target) {
    	
//    	@Override
//        public List<Organization> findAllWithExclude(Organization excludeOraganization) {
//            //TODO 改成not exists 利用索引
//            final String sql = "select id, name, parent_id, parent_ids, available from sys_organization where id!=? and parent_ids not like ?";
//            return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Organization.class), excludeOraganization.getId(), excludeOraganization.makeSelfAsParentIds() + "%");
//        }
//
//        @Override
//        public void move(Organization source, Organization target) {
//            String moveSourceSql = "update sys_organization set parent_id=?,parent_ids=? where id=?";
//            jdbcTemplate.update(moveSourceSql, target.getId(), target.getParentIds(), source.getId());
//            String moveSourceDescendantsSql = "update sys_organization set parent_ids=concat(?, substring(parent_ids, length(?))) where parent_ids like ?";
//            jdbcTemplate.update(moveSourceDescendantsSql, target.makeSelfAsParentIds(), source.makeSelfAsParentIds(), source.makeSelfAsParentIds() + "%");
//        }
        //organizationDao.updateByPrimaryKey(record)(source, target);
    }
}
