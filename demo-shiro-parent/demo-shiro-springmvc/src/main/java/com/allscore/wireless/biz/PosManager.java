package com.allscore.wireless.biz;

import java.util.List;
import java.util.Map;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.MemberPublic;
import com.allscore.wireless.dao.PosInfo;


public interface PosManager {

	Map getPosInfoRkByMap(Pager pager,Map map);
	
	int addPosInfoByList(List<PosInfo> list);
	
	List<PosInfo> getPosInfoByList(Pager pager,Map map);
	
	Map getPosInfoCkByMap(Pager pager,Map map);
	
	List<PosInfo> getPosInfoCkByList(Pager pager,Map map);
	
	List<MemberPublic> getMerchantNameByList(Pager pager,Map map);
	
	void updatePosInfoFpByList(Map map);
	
	int updatePosInfoJcByList(Map map);
	
	int delPosInfoById(String id);
	
}
