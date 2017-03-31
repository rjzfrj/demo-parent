package com.github.zhangkaitao.shiro.chapter16.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.zhangkaitao.shiro.chapter16.dao.RoleMapper;
import com.github.zhangkaitao.shiro.chapter16.entity.Role;
import com.github.zhangkaitao.shiro.chapter16.entity.RoleExample;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleDao;
    @Autowired
    private ResourceService resourceService;

    public int createRole(Role role) {
        return roleDao.insert(role);
    }

    public int updateRole(Role role) {
        return roleDao.updateByPrimaryKey(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteByPrimaryKey(roleId);
    }

    @Override
    public Role findOne(Long roleId) {
        return roleDao.selectByPrimaryKey(roleId);
    }

    @Override
    public List<Role> findAll() {
    	RoleExample roleEx=new RoleExample();
        return roleDao.selectByExample(roleEx);
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        RoleExample example=new RoleExample();
        RoleExample.Criteria criteria=example.createCriteria();
        List<Long> ids=new ArrayList<Long>();
        for(Long roleId : roleIds) {
            ids.add(roleId);
        }
        criteria.andIdIn(ids);
        List<Role> roleList=roleDao.selectByExample(example);
        for (Role role : roleList) {
        	roles.add(role.getRole());
		}
        return roles;
    }

    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
//        for(Long roleId : roleIds) {
//            Role role = findOne(roleId);
//            if(role != null) {
//                resourceIds.addAll(role.getResourceIds());
//            }
//        }
        RoleExample roleEx=new RoleExample();
        RoleExample.Criteria criteria= roleEx.createCriteria();
        List<Long> ids=new ArrayList<Long>();
        for(Long roleId : roleIds) {
            ids.add(roleId);
        }
        criteria.andIdIn(ids);
        List<Role> roleList=roleDao.selectByExample(roleEx);
       for (Role role : roleList) {
           String[] resoIds= role.getResourceIds().split(",");
           Set<Long> setReso= (Set<Long>) Arrays.asList(stringToLong(resoIds));
    	   resourceIds.addAll(setReso);
       }
        return resourceService.findPermissions(resourceIds);
    }
    
    public static Long[] stringToLong(String stringArray[]) {
        if (stringArray == null)
            return null;
        return (Long[]) ConvertUtils.convert(stringArray, Long.class);
    }
}
