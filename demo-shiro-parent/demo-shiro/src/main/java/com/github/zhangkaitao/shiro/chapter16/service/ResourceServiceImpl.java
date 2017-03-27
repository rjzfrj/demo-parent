package com.github.zhangkaitao.shiro.chapter16.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.zhangkaitao.shiro.chapter16.dao.ResourceMapper;
import com.github.zhangkaitao.shiro.chapter16.entity.Resource;
import com.github.zhangkaitao.shiro.chapter16.entity.ResourceExample;
import com.github.zhangkaitao.shiro.chapter16.entity.ResourceExample.Criteria;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Service

public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceDao;

    @Override
    public int createResource(Resource resource) {
        return resourceDao.insert(resource);
    }

    @Override
    public int updateResource(Resource resource) {
        return resourceDao.updateByPrimaryKey(resource);
    }

    @Override
    public void deleteResource(Long resourceId) {
        resourceDao.deleteByPrimaryKey(resourceId);
    }

    @Override
    public Resource findOne(Long resourceId) {
        return resourceDao.selectByPrimaryKey(resourceId);
    }

    @Override
    public List<Resource> findAll() {
    	ResourceExample example=new ResourceExample();
    	Criteria criteria=example.createCriteria();
        return resourceDao.selectByExample(example);
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
//        for(Long resourceId : resourceIds) {
//            Resource resource = findOne(resourceId);
//            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
//                permissions.add(resource.getPermission());
//            }
//        }
        ResourceExample example=new ResourceExample();
    	Criteria criteria=example.createCriteria();
    	List<Long> list=new ArrayList<Long>();
    	list.addAll(resourceIds);
    	criteria.andIdIn(list);
        List<Resource> resourceList=resourceDao.selectByExample(example);
        for (Resource resource : resourceList) {
        	if(!StringUtils.isEmpty(resource.getPermission())) {
        	 permissions.add(resource.getPermission());
        	}
		}
        return permissions;
    }

    @Override
    public List<Resource> findMenus(Set<String> permissions) {
        List<Resource> allResources = findAll();
        List<Resource> menus = new ArrayList<Resource>();
        for(Resource resource : allResources) {
            if(resource.isRootNode()) {
                continue;
            }
            if(resource.getType() != Resource.ResourceType.menu) {
                continue;
            }
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, Resource resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
