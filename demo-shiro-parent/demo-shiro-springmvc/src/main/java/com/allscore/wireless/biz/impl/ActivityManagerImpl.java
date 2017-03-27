package com.allscore.wireless.biz.impl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;


import com.allscore.wireless.biz.ActivityManager;
import com.allscore.wireless.common.DateUtil;
import com.allscore.wireless.common.Pager;

import com.allscore.wireless.dao.PromotionPlan;
import com.allscore.wireless.dao.PromotionPlanExample;
import com.allscore.wireless.dao.PromotionPlanMapper;


public class ActivityManagerImpl implements ActivityManager {
	
	private static final Logger logger = Logger.getLogger(ActivityManagerImpl.class);
	
	@Autowired
	PromotionPlanMapper promotionPlanMapper;
    /**
     * 查看促销方案列表
     */
	public Map getPromotionPlanByMap(Pager pager, Map map) {
		String merchant_name = (String) map.get("merchant_name");
		String type = (String) map.get("type");

		String strDate = (String) map.get("strDate");
		String endDate = (String) map.get("endDate");
		
		
		PromotionPlanExample promotionPlanExample = new PromotionPlanExample();
		PromotionPlanExample.Criteria promotionPlanExampleCir = promotionPlanExample.createCriteria();
		if(null!=merchant_name && !"".equals(merchant_name)){
			promotionPlanExampleCir.andMERCHANT_NAMELike("%"+merchant_name+"%");
		}
		if(null!=type && !"".equals(type)){
			promotionPlanExampleCir.andTYPEEqualTo(type);
		}
		
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			
			promotionPlanExampleCir.andSTART_TIMEBetween(strDate, endDate);
			
		}
		
		int total = promotionPlanMapper.countByExample(promotionPlanExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			promotionPlanExample.setLimitStart(pager.getOffest());
			promotionPlanExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		
		promotionPlanExample.setOrderByClause("create_time desc");
		List<PromotionPlan> list = this.promotionPlanMapper.selectByExample(promotionPlanExample);
		
		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
	

	public void addPromotionPlanByMap(Map map) {
		PromotionPlanExample promotionPlanExample = new PromotionPlanExample();
	    String[] amount = (String[]) map.get("amount");
	    String reward = (String) map.get("reward");
	    String merchant_code = (String) map.get("merchant_code");//商户编号
	    String validDay = (String) map.get("validDay");
	    String stilValid = (String) map.get("stilValid");
	    String validDate = (String) map.get("validDate");
	    String type = (String) map.get("type");
	    
	    PromotionPlan promotionPlan = new PromotionPlan();
	    promotionPlan.setTYPE(type);
	    promotionPlan.setSTART_TIME(DateUtil.getFormatDate());
	    
	    promotionPlan.setCREATE_TIME(new Date());
	    promotionPlan.setUPDATE_TIME(new Date());
	    promotionPlan.setMERCHANT_CODE(merchant_code);
	    promotionPlan.setSTATUS("00");
	    
	    //优惠券有效期--天数
	    if(validDay!=null&&!"".equals(validDay)){
	    	promotionPlan.setVALID_DATE(validDay);
	    }
	    //优惠券有效期--长期有效
	    if(stilValid!=null&&!"".equals(stilValid)){
	    	promotionPlan.setVALID_DATE(stilValid);
	    }
	    
	    if(amount!=null && amount.length>0){
	    	//充值
	    	if(type.equals("00")){
			    for(int i=0;i< amount.length/2;i++){
			    	promotionPlan.setID(UUID.randomUUID().toString());
			    	String rule = amount[i*2];
			    	String rewardCz = amount[i*2+1];
			    	promotionPlan.setRULE(rule);
			    	promotionPlan.setREWARD(rewardCz);
			    	this.promotionPlanMapper.insertSelective(promotionPlan);
			    	
			    }
	    	}
	    	//消费
	    	if(type.equals("01")){
			    for(int i=0;i< amount.length/3;i++){
			    	promotionPlan.setID(UUID.randomUUID().toString());
			    	String rule = amount[i*3]+"~"+amount[i*3+1];
			    	String rewardXf = amount[i*3+2];
			    	promotionPlan.setRULE(rule);
			    	promotionPlan.setREWARD(rewardXf);
			    	promotionPlan.setCONSUME_MIN(new BigDecimal(amount[i*3]));
			    	promotionPlan.setCONSUME_MAX(new BigDecimal(amount[i*3+1]));
			    	this.promotionPlanMapper.insertSelective(promotionPlan);
			    	
			    }
	    	}
	    	
	    }
	    
	    
        //点评、注册
	    if(reward!=null && !"".equals(reward)){
	    	//点评、注册
	    	promotionPlan.setID(UUID.randomUUID().toString());
	    	
	    	if(type.equals("02")){
	    		promotionPlan.setRULE("comment");
	    	}else if(type.equals("03")){
	    		promotionPlan.setRULE("login");
	    	}
	    	
	    	promotionPlan.setREWARD(reward);
	    	this.promotionPlanMapper.insertSelective(promotionPlan);
	    }
	    
		
	}

	public int delPromotionPlanByID(String id) {
		int i = this.promotionPlanMapper.deleteByPrimaryKey(id);
		logger.info("=======删除ID为:"+id+"的记录"+i+"条=======");
		return i;
		
		
	}

	public int checkIsExist(Map map) {
		String[] amount = (String[]) map.get("amount");
	    
	    String reward = (String) map.get("reward");
	    String validDay = (String) map.get("validDay");
	    String stilValid = (String) map.get("stilValid");
	    String validDate = (String) map.get("validDate");
	    String type = (String) map.get("type");
	    String merchant_code = (String) map.get("merchant_code");
	    
	    PromotionPlanExample promotionPlanExample = new PromotionPlanExample();
	    PromotionPlanExample.Criteria promotionPlanExampleCir = promotionPlanExample.createCriteria();
	    promotionPlanExampleCir.andTYPEEqualTo(type);
	    promotionPlanExampleCir.andMERCHANT_CODEEqualTo(merchant_code);
	    
	    
	    
	    //优惠券有效期--天数
	    if(validDay!=null&&!"".equals(validDay)){
	    	//promotionPlanExampleCir.andVALID_DATEEqualTo(validDay);
	    }
	    //优惠券有效期--长期有效
	    if(stilValid!=null&&!"".equals(stilValid)){
	    	//promotionPlanExampleCir.andVALID_DATEEqualTo(stilValid);
	    }
	    
	    if(type.equals("00")){
	    	PromotionPlanExample promotionPlanExampleCZ = new PromotionPlanExample();
		    PromotionPlanExample.Criteria promotionPlanExampleCirCZ = promotionPlanExampleCZ.createCriteria();
		    promotionPlanExampleCirCZ.andTYPEEqualTo(type);
		    promotionPlanExampleCirCZ.andMERCHANT_CODEEqualTo(merchant_code);
		    promotionPlanExampleCirCZ.andRULEEqualTo(amount[0]);
	    	return this.promotionPlanMapper.countByExampleAdd(promotionPlanExampleCZ);
	    }else if(type.equals("01")){
	    	int i=0;
	    	PromotionPlanExample promotionPlanExampleXF1 = new PromotionPlanExample();
		    PromotionPlanExample.Criteria promotionPlanExampleCirXF1 = promotionPlanExampleXF1.createCriteria();
		    promotionPlanExampleCirXF1.andTYPEEqualTo(type);
		    promotionPlanExampleCirXF1.andMERCHANT_CODEEqualTo(merchant_code);
		    promotionPlanExampleCirXF1.andCONSUME_MINLessThanOrEqualTo(new BigDecimal(amount[0]));
		    promotionPlanExampleCirXF1.andCONSUME_MAXGreaterThanOrEqualTo(new BigDecimal(amount[0]));
	    	i= this.promotionPlanMapper.countByExampleAdd(promotionPlanExampleXF1);
	    	if(i>0){
	    		return i;	
	    	}else{
	    		PromotionPlanExample promotionPlanExampleXF2 = new PromotionPlanExample();
	    	    PromotionPlanExample.Criteria promotionPlanExampleCirXF2 = promotionPlanExampleXF2.createCriteria();
	    	    promotionPlanExampleCirXF2.andTYPEEqualTo(type);
	    	    promotionPlanExampleCirXF2.andMERCHANT_CODEEqualTo(merchant_code);
	    	    promotionPlanExampleCirXF2.andCONSUME_MINLessThanOrEqualTo(new BigDecimal(amount[1]));
	    	    promotionPlanExampleCirXF2.andCONSUME_MAXGreaterThanOrEqualTo(new BigDecimal(amount[1]));
	    		i = this.promotionPlanMapper.countByExampleAdd(promotionPlanExampleXF2);
	    		if(i>0){
	    			return i;
	    		}else{
	    			PromotionPlanExample promotionPlanExampleXF3 = new PromotionPlanExample();
	    		    PromotionPlanExample.Criteria promotionPlanExampleCirXF3 = promotionPlanExampleXF3.createCriteria();
	    		    promotionPlanExampleCirXF3.andTYPEEqualTo(type);
	    		    promotionPlanExampleCirXF3.andMERCHANT_CODEEqualTo(merchant_code);
	    		    promotionPlanExampleCirXF3.andCONSUME_MINGreaterThanOrEqualTo(new BigDecimal(amount[0]));
	    		    promotionPlanExampleCirXF3.andCONSUME_MAXLessThanOrEqualTo(new BigDecimal(amount[1]));
		    		return this.promotionPlanMapper.countByExampleAdd(promotionPlanExampleXF3);
	    		}
	    	}

	    }
		return this.promotionPlanMapper.countByExampleAdd(promotionPlanExample);
	    
		
	}


	public PromotionPlan getPromotionPlanByID(String id) throws Exception{
		PromotionPlan promotionPlan = this.promotionPlanMapper.selectByPrimaryKey(id);
	    
		Field[] fields = promotionPlan.getClass().getDeclaredFields();
		for(Field field :fields){
			field.setAccessible(true);
			if(field.get(promotionPlan)==null){
				if(field.getType().getName().equals("java.lang.String"))
				{
					field.set(promotionPlan, "");	
				}
				if(field.getType().getName().equals("java.lang.Integer"))
				{
					field.set(promotionPlan, 0);	
				}   
			}
		}
		return promotionPlan;
	}
	

}
