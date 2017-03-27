package com.allscore.wireless.biz;

import java.util.List;
import java.util.Map;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.MemberPublic;
import com.allscore.wireless.dao.Users;


public interface UsersManager {

	
	List<Users> getUsersByList(Pager pager,Map map);
	
	Map getUsersByMap(Pager pager,Map map);

	Map getUsersByMobile(Pager pager,String member_card);
	
}
