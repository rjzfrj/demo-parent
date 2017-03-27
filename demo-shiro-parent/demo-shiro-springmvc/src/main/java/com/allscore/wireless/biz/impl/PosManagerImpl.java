package com.allscore.wireless.biz.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.allscore.wireless.biz.PosManager;
import com.allscore.wireless.common.DateUtil;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.MemberPublic;
import com.allscore.wireless.dao.MemberPublicMapper;
import com.allscore.wireless.dao.MemberPublicWithBLOBs;
import com.allscore.wireless.dao.PosInfo;
import com.allscore.wireless.dao.PosInfoExample;
import com.allscore.wireless.dao.PosInfoMapper;

public class PosManagerImpl implements PosManager {
	
	private static final Logger  logger = Logger.getLogger(PosManagerImpl.class);
	
	@Autowired
	PosInfoMapper posInfoMapper;
	
	@Autowired
	MemberPublicMapper memberPublicMapper;
    
	/**
	 * pos机信息入库查询
	 */
	public Map getPosInfoRkByMap(Pager pager, Map map) {
		// TODO Auto-generated method stub
		PosInfoExample posInfoExample = new PosInfoExample();
		PosInfoExample.Criteria posInfoExampleCir = posInfoExample.createCriteria();
	    String pos_sn = (String) map.get("pos_sn");
	    String factory_name = (String) map.get("factory_name");
	    String pos_model = (String) map.get("pos_model");
	    String communication = (String) map.get("communication");
	    
		
		if(null!=pos_sn && !"".equals(pos_sn)){
			posInfoExampleCir.andPOS_SNLike("%"+pos_sn+"%");
		}
		if(null!=factory_name && !"".equals(factory_name)){
			posInfoExampleCir.andFACTORY_NAMELike("%"+factory_name+"%");
		}	
		if(null!=pos_model && !"".equals(pos_model)){
			posInfoExampleCir.andPOS_MODELLike("%"+pos_model+"%");
		}
		if(null!=communication && !"".equals(communication)){
			posInfoExampleCir.andCOMMUNICATIONLike("%"+communication+"%");
		}

		int total = posInfoMapper.countByExample(posInfoExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			posInfoExample.setLimitStart(pager.getOffest());
			posInfoExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		posInfoExample.setOrderByClause("create_time desc");
		List<PosInfo> list = posInfoMapper.selectByExample(posInfoExample);

		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
    /**
     * pos机信息导入
     *
     */
	@Transactional(propagation=Propagation.REQUIRED,readOnly = false, rollbackFor = RuntimeException.class)
	public int addPosInfoByList(List<PosInfo> list) {
		
		
		int sum=0;
		for(int i=0;i<list.size();i++){
			PosInfoExample posInfoExample = new PosInfoExample();
			PosInfoExample.Criteria posInfoExampleCir = posInfoExample.createCriteria();
			int cnt =0;
			String id = UUID.randomUUID().toString();
			list.get(i).setID(id);
			//posInfoExample.
			posInfoExampleCir.andPOS_SNEqualTo(list.get(i).getPOS_SN());
			logger.info("POS_SN:"+list.get(i).getPOS_SN());
			int total = posInfoMapper.countByExample(posInfoExample);
			logger.info("total:"+total);
			//list.get(i).setPOS_SN(id);
			if(total==0){
				cnt = this.posInfoMapper.insertSelective(list.get(i));
				sum += cnt;
			}
			
		}
		
		return sum;
	}
	/**
	 * pos机信息导出
	 */
	public List<PosInfo> getPosInfoByList(Pager pager, Map map) {
		PosInfoExample posInfoExample = new PosInfoExample();
		List<PosInfo> listPosInfo = this.posInfoMapper.selectByExampleExport(posInfoExample);
		return listPosInfo;
	}
	/**
	 * pos机信息出库查询
	 */
	public Map getPosInfoCkByMap(Pager pager, Map map) {
        PosInfoExample posInfoExample = new PosInfoExample();
        PosInfoExample.Criteria posInfoExampleCir = posInfoExample.createCriteria();
	    String pos_sn = (String) map.get("pos_sn");
	    String factory_name = (String) map.get("factory_name");
	    String pos_model = (String) map.get("pos_model");
	    String status = (String) map.get("status");
	    String merchantCode = (String) map.get("merchantCode");
	    
		if(null!=pos_sn && !"".equals(pos_sn)){
			posInfoExampleCir.andPOS_SNLike("%"+pos_sn+"%");
		}
		if(null!=factory_name && !"".equals(factory_name)){
			posInfoExampleCir.andFACTORY_NAMELike("%"+factory_name+"%");
		}	
		if(null!=pos_model && !"".equals(pos_model)){
			posInfoExampleCir.andPOS_MODELLike("%"+pos_model+"%");
		}
		if(null!=status && !"".equals(status)){
			posInfoExampleCir.andSTATUSEqualTo(status);
		}
		if(null!=merchantCode && !"".equals(merchantCode)){
			posInfoExampleCir.andMERCHANT_CODEEqualTo(merchantCode);
		}

		int total = posInfoMapper.countByExample(posInfoExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			posInfoExample.setLimitStart(pager.getOffest());
			posInfoExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		posInfoExample.setOrderByClause("create_time desc");
		List<PosInfo> list = posInfoMapper.selectPosCkByExample(posInfoExample);

		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
	
	/**
	 * pos机出库信息导出
	 */
	public List<PosInfo> getPosInfoCkByList(Pager pager, Map map) {
		PosInfoExample posInfoExample = new PosInfoExample();
		List<PosInfo> listPosInfo = this.posInfoMapper.selectCkByExampleExport(posInfoExample);
		return listPosInfo;
	}
	public List<MemberPublic> getMerchantNameByList(Pager pager, Map map) {
		// TODO Auto-generated method stub
		return this.posInfoMapper.selectMerchantName();
	}
	/**
	 * 批量分配POS机
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly = false, rollbackFor = RuntimeException.class)
	public void updatePosInfoFpByList(Map map) {
		PosInfoExample posInfoExample = new PosInfoExample();
		
		PosInfo posInfo = new PosInfo();
        //商户编号
		String merchantId = (String) map.get("merchantId");
		
		MemberPublicWithBLOBs memberPublicWithBLOBs = this.memberPublicMapper.selectByMerchantID(merchantId);
		//pos机id数组
		String[] strId = (String[]) map.get("strId");
		posInfo.setMERCHANT_CODE(merchantId);
		posInfo.setSTATUS("01");
		posInfo.setMERCHANT_NAME(memberPublicWithBLOBs.getMERCHANT_NAME());
		List list = new ArrayList();
		for(String str : strId){
			list.add(str);
			
		}
		posInfoExample.createCriteria().andIDIn(list);
		
		this.posInfoMapper.updateByExampleSelective(posInfo, posInfoExample);
		// TODO Auto-generated method stub
		
	}
	/**
	 * 批量解除POS机绑定
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly = false, rollbackFor = RuntimeException.class)
	public int updatePosInfoJcByList(Map map) {
        PosInfoExample posInfoExample = new PosInfoExample();
		PosInfo posInfo = new PosInfo();

		//pos机id数组
		String[] strId = (String[]) map.get("strId");
		posInfo.setMERCHANT_CODE("");
		posInfo.setSTATUS("00");
		posInfo.setMERCHANT_NAME("");
		posInfo.setUPDATE_TIME(DateUtil.getFormatDate());
		List list = new ArrayList();
		for(String str : strId){
			list.add(str);
		}
		posInfoExample.createCriteria().andIDIn(list);
		
		int cnt = this.posInfoMapper.updateByExampleSelective(posInfo, posInfoExample);
		return cnt;
	}
	/**
	 * 删除pos信息
	 */
	public int delPosInfoById(String id) {
		PosInfoExample posInfoExample = new PosInfoExample();
		PosInfoExample.Criteria posInfoExampleCir = posInfoExample.createCriteria();
		posInfoExampleCir.andIDEqualTo(id);
		int cnt = this.posInfoMapper.deleteByExample(posInfoExample);
		return cnt;
	}

}
