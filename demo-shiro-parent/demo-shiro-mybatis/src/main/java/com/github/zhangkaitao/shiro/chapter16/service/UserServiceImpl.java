package com.github.zhangkaitao.shiro.chapter16.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.zhangkaitao.shiro.chapter16.dao.UserMapper;
import com.github.zhangkaitao.shiro.chapter16.entity.User;
import com.github.zhangkaitao.shiro.chapter16.entity.UserExample;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;

    /**
     * 创建用户
     * @param user
     */
    public int createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        return userDao.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateByPrimaryKey(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteByPrimaryKey(userId);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword) {
        User user =userDao.selectByPrimaryKey(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateByPrimaryKey(user);
    }

    @Override
    public User findOne(Long userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> findAll() {
    	UserExample userEx=new UserExample();
    	UserExample.Criteria criteria=userEx.createCriteria();
    	userEx.setOrderByClause("id");
        return userDao.selectByExample(userEx);
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.selectByPrimaryLongName(username);
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        String roleIds=user.getRoleIds();
        String[] role=roleIds.split(",");
        Long[] roles=stringToLong(role);
        return roleService.findRoles(roles);
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        User user =findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        String roleIds=user.getRoleIds();
        String[] role=roleIds.split(",");
        Long[] roles=stringToLong(role);
        return roleService.findPermissions(roles);
    }

    public static Long[] stringToLong(String stringArray[]) {
        if (stringArray == null)
            return null;
        return (Long[]) ConvertUtils.convert(stringArray, Long.class);
    }
}
