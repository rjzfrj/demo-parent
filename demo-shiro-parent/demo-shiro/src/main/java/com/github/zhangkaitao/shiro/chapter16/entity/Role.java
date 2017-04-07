package com.github.zhangkaitao.shiro.chapter16.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.StringUtils;

public class Role {


    private Long id; //编号
    private String role; //角色标识 程序中判断使用,如"admin"
    private String description; //角色描述,UI界面显示使用
    private String resourceIds; //拥有的资源
    private List<Long> resourceIdList; //拥有的资源
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    public Role() {
    }

    public Role(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  
    public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

//	public String getResourceIdsStr() {
//        if(CollectionUtils.isEmpty(resourceIds)) {
//            return "";
//        }
//        StringBuilder s = new StringBuilder();
//        for(Long resourceId : resourceIds) {
//            s.append(resourceId);
//            s.append(",");
//        }
//        return s.toString();
//    }
//
//    public void setResourceIdsStr(String resourceIdsStr) {
//        if(StringUtils.isEmpty(resourceIdsStr)) {
//            return;
//        }
//        String[] resourceIdStrs = resourceIdsStr.split(",");
//        for(String resourceIdStr : resourceIdStrs) {
//            if(StringUtils.isEmpty(resourceIdStr)) {
//                continue;
//            }
//            getResourceIds().add(Long.valueOf(resourceIdStr));
//        }
//    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    
    public List<Long> getResourceIdList() {
    	String[] resourceids=resourceIds.split(",");
    	Long[] re=(Long[]) ConvertUtils.convert(resourceids, Long.class);
		   Set<Long> setResource=new HashSet<Long>();
		CollectionUtils.addAll(setResource, re);
		List<Long> list =new ArrayList<Long>();
		list.addAll(setResource);
		return list;
	}

	public void setResourceIdList(List<Long> resourceIdList) {
		this.resourceIdList = resourceIdList;
	}

	@Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", resourceIds=" + resourceIds +
                ", available=" + available +
                '}';
    }
    
}