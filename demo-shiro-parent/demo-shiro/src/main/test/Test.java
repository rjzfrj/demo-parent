import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.zhangkaitao.shiro.chapter16.entity.Organization;
import com.github.zhangkaitao.shiro.chapter16.entity.Resource;
import com.github.zhangkaitao.shiro.chapter16.entity.Role;
import com.github.zhangkaitao.shiro.chapter16.entity.User;
import com.github.zhangkaitao.shiro.chapter16.service.OrganizationService;
import com.github.zhangkaitao.shiro.chapter16.service.ResourceService;
import com.github.zhangkaitao.shiro.chapter16.service.RoleService;
import com.github.zhangkaitao.shiro.chapter16.service.UserService;

public class Test {

	public static void main(String[] args) {
		ApplicationContext applContext = new ClassPathXmlApplicationContext("spring-config.xml");
		// UserService userService = (UserService)
		// applContext.getBean(UserService.class);
		// String username = "admin";
		// Set<String> setRoles = userService.findRoles(username);
		// Set<String> setPermissions = userService.findPermissions(username);
		// User user = userService.findByUsername(username);
		// User user1 = userService.findOne(1L);
		// List<User> listu = userService.findAll();
		// System.out.println("setRoles" + setRoles + "setPermissions:" +
		// setPermissions);
		//
		RoleService roleService = (RoleService) applContext.getBean(RoleService.class);
		// Role role = roleService.findOne(1L);
		// List<Role> rolelist = roleService.findAll();
		// Set<String> setroles = roleService.findRoles(1L, 2L);
		Long[] roleIds = new Long[3];
		roleIds[0] = 1L;
		roleIds[1] = 2L;
		roleIds[2] = 3L;
		Set<String> permisson = roleService.findPermissions(roleIds);
		// System.out.println("setRoles" + setRoles + "setPermissions:" +
		// setPermissions);

		ResourceService resourceService = applContext.getBean(ResourceService.class);
		List<Resource> resourceList = resourceService.findAll();
		Resource resource = resourceService.findOne(1L);
		List<Resource> listResou = resourceService.findAll();
		Set<Long> resourceIds = new HashSet<Long>();
		resourceIds.add(1L);
		resourceIds.add(2L);
		resourceIds.add(3L);
		Set<String> permissonst = resourceService.findPermissions(resourceIds);
		List<Resource> listresou = resourceService.findMenus(permissonst);

		
		OrganizationService organizationService = (OrganizationService) applContext.getBean(OrganizationService.class);
		
		  Organization organization=organizationService.findOne(1L);
		    List<Organization> list= organizationService.findAll();

		    Object aa=organizationService.findAllWithExclude(organization);

		// Long[] data = new Long[3];
		// data[0]=1L;
		// data[1]=2L;
		// data[2]=3L;
		// Set<Long> set=new HashSet<Long>();
		// CollectionUtils.addAll(set, data);
		// List<Long> list = Arrays.asList(data);
		// System.out.println("列表中的元素数量是：" + list.size());
		// System.out.println("2列表中的元素数量是：" + set.size());
	}
}
