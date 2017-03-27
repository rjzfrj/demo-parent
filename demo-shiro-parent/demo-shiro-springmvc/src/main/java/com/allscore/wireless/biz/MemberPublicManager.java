package com.allscore.wireless.biz;

import java.util.List;
import java.util.Map;

import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.MemberPublic;
import com.allscore.wireless.dao.MemberPublicWithBLOBs;


public interface MemberPublicManager {
	
	MemberPublicWithBLOBs getMemberPublicByID(String id) throws Exception;
	
    MemberPublicWithBLOBs getMemberPublicByMerchantID(String merchantID) throws Exception;
	
	Map getMemberPublicByMap(Pager pager,Map map) throws Exception;
	
	String delMemberPublicByMerchant_Code(String merchant_code);
	
	boolean editMemberPublicByID(MemberPublicWithBLOBs memberPublicWithBLOBs);
	
	boolean addMemberPublic(MemberPublicWithBLOBs memberPublicWithBLOBs);
	
	String randomMath();
	
	String getMerchantCode();
	
	Map getMsgTemplateByMap(Pager pager,Map map);
	
	int addMsgTemplateByMap(Map map) throws Exception;
	
	int delMsgTemplateByID(String id) throws Exception;

	
}
