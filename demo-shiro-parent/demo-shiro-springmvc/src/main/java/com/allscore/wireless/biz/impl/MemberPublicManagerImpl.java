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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.allscore.wireless.biz.MemberPublicManager;
import com.allscore.wireless.common.Pager;
import com.allscore.wireless.dao.FundPool;
import com.allscore.wireless.dao.FundPoolExample;
import com.allscore.wireless.dao.FundPoolMapper;
import com.allscore.wireless.dao.MemberPublic;
import com.allscore.wireless.dao.MemberPublicExample;
import com.allscore.wireless.dao.MemberPublicMapper;
import com.allscore.wireless.dao.MemberPublicWithBLOBs;
import com.allscore.wireless.dao.MerchantRate;
import com.allscore.wireless.dao.MerchantRateExample;
import com.allscore.wireless.dao.MerchantRateMapper;
import com.allscore.wireless.dao.MsgTemplate;
import com.allscore.wireless.dao.MsgTemplateExample;
import com.allscore.wireless.dao.MsgTemplateMapper;
import com.allscore.wireless.dao.PosInfo;
import com.allscore.wireless.dao.PosInfoExample;
import com.allscore.wireless.dao.PosInfoMapper;
import com.allscore.wireless.dao.UserBaseExample;
import com.allscore.wireless.dao.UserBaseMapper;


public class MemberPublicManagerImpl implements MemberPublicManager {
	
	private static final Logger logger = Logger.getLogger(MemberPublicManagerImpl.class);
	
	@Autowired
	MemberPublicMapper memberPublicMapper;
	
	@Autowired
	MsgTemplateMapper msgTemplateMapper;
	
	@Autowired
	MerchantRateMapper merchantRateMapper;
	
	@Autowired
	FundPoolMapper fundPoolMapper;
	
	@Autowired
	PosInfoMapper posInfoMapper;
    
	@Autowired
	UserBaseMapper userBaseMapper;
	
	
	//查询商户明细信息
	public MemberPublicWithBLOBs getMemberPublicByID(String id) throws Exception {
		//获取商户明细信息
		MemberPublicWithBLOBs memberPublicWithBLOBs = this.memberPublicMapper.selectByPrimaryKey(id);
		String merchantCode = memberPublicWithBLOBs.getMERCHANT_CODE();//商户编号
		MerchantRateExample merchantRateExample = new MerchantRateExample();
		MerchantRateExample.Criteria merchantRateExampleCir = merchantRateExample.createCriteria();
		merchantRateExampleCir.andMERCHANT_CODEEqualTo(merchantCode);
		//获取商户费率信息
		List<MerchantRate> listMerchantRate = this.merchantRateMapper.selectByExample(merchantRateExample);
		if(listMerchantRate!=null && listMerchantRate.size()>0){
			for(MerchantRate merchantRate : listMerchantRate){
				String chnCode = merchantRate.getCHN_CODE();//渠道号
				BigDecimal rate = merchantRate.getRATE();
				if("00".equals(chnCode)){
					memberPublicWithBLOBs.setBANK_RATE(rate);
				}
				if("01".equals(chnCode)){
					memberPublicWithBLOBs.setWECHAT_RATE(rate);
				}
				if("02".equals(chnCode)){
					memberPublicWithBLOBs.setPAY_RATE(rate);
				}
				if("03".equals(chnCode)){
					memberPublicWithBLOBs.setMEMBERCARD_RATE(rate);
				}
				if("04".equals(chnCode)){
					memberPublicWithBLOBs.setWALLET_RATE(rate);
				}
				if("05".equals(chnCode)){
					memberPublicWithBLOBs.setALLSCORE_RATE(rate);
				}
				if("06".equals(chnCode)){
					memberPublicWithBLOBs.setCREDIT_CARD_RATE(rate);
				}
			}
		}
		//将查询结果中为null值得进行相应处理
		Field[] fields = memberPublicWithBLOBs.getClass().getSuperclass().getDeclaredFields();
		for(Field field :fields){
			field.setAccessible(true);
			if(field.get(memberPublicWithBLOBs)==null){
				if(field.getType().getName().equals("java.lang.String"))
				{
					field.set(memberPublicWithBLOBs, "");	
				}
				if(field.getType().getName().equals("java.lang.Integer"))
				{
					field.set(memberPublicWithBLOBs, 0);	
				}
				if(field.getType().getName().equals("java.math.BigDecimal"))
				{
					field.set(memberPublicWithBLOBs, new BigDecimal("0"));	
				}
			}
		}
		return memberPublicWithBLOBs;
	}
	
	public MemberPublicWithBLOBs getMemberPublicByMerchantID(String merchantID) throws Exception {
		MemberPublicWithBLOBs memberPublicWithBLOBs = memberPublicMapper.selectByMerchantID(merchantID);
		//将查询结果中为null值得进行相应处理
		Field[] fields = memberPublicWithBLOBs.getClass().getSuperclass().getDeclaredFields();
		for(Field field :fields){
			field.setAccessible(true);
			if(field.get(memberPublicWithBLOBs)==null){
				if(field.getType().getName().equals("java.lang.String"))
				{
					field.set(memberPublicWithBLOBs, "");	
				}
				if(field.getType().getName().equals("java.lang.Integer"))
				{
					field.set(memberPublicWithBLOBs, 0);	
				}
				if(field.getType().getName().equals("java.math.BigDecimal"))
				{
					field.set(memberPublicWithBLOBs, new BigDecimal("0"));	
				}
			}
		}
		return memberPublicWithBLOBs;
	}
	
	/**
	 * 查询商户列表
	 * @throws IllegalAccessException 
	 * @throws Exception 
	 */
	public Map getMemberPublicByMap(Pager pager,Map map) throws Exception {
		// TODO Auto-generated method stub
		MemberPublicExample memberPublicExample = new MemberPublicExample();
		MemberPublicExample.Criteria memberPublicExampleCir = memberPublicExample.createCriteria();
		String merchant_name = (String)map.get("merchant_name");
		String MERCHANT_CODE = (String)map.get("MERCHANT_CODE");
		String strDate = (String)map.get("strDate");
		String endDate = (String)map.get("endDate");
		if(null!=merchant_name && !"".equals(merchant_name)){
			memberPublicExampleCir.andMERCHANT_NAMELike("%"+merchant_name+"%");
		}
		if(null!=MERCHANT_CODE && !"".equals(MERCHANT_CODE)){
			memberPublicExampleCir.andMERCHANT_CODELike("%"+MERCHANT_CODE+"%");
		}
		if(null!=strDate && null!=endDate && !"".equals(strDate) && !"".equals(endDate)){
			memberPublicExample.setStrDate(strDate);
			memberPublicExample.setEndDate(endDate);
	
		}
		int total = memberPublicMapper.countByExample(memberPublicExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			memberPublicExample.setLimitStart(pager.getOffest());
			memberPublicExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		memberPublicExample.setOrderByClause("create_time desc");
		List<MemberPublic> list = memberPublicMapper.selectByExample(memberPublicExample);
	    //将null值转化为""
		if(list!=null && list.size()>0){
		    for(MemberPublic memberPublic:list){
				Field[] fields = memberPublic.getClass().getDeclaredFields();
				for(Field field :fields){
					field.setAccessible(true);
					if(field.get(memberPublic)==null){
						//System.out.println(field.getName());
						if(field.getType().getName().equals("java.lang.String"))
						{
							field.set(memberPublic, "");	
						}
						if(field.getType().getName().equals("java.lang.Integer"))
						{
							field.set(memberPublic, 0);	
						}
						if(field.getType().getName().equals("java.math.BigDecimal"))
						{
							field.set(memberPublic, new BigDecimal("0"));	
						}
					}
				}
		    }
		}
		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}
    /**
     * 删除商户信息
     * @param merchant_code
     * @return
     */
	@Transactional(propagation=Propagation.REQUIRED,readOnly = false, rollbackFor = RuntimeException.class)
	public String delMemberPublicByMerchant_Code(String merchant_code) {
		//判断该商户下是否有pos机
		PosInfoExample posInfoExample = new PosInfoExample();
		PosInfoExample.Criteria posInfoExampleCir = posInfoExample.createCriteria();
		posInfoExampleCir.andMERCHANT_CODEEqualTo(merchant_code);
		List<PosInfo> listPosInfo = this.posInfoMapper.selectByExample(posInfoExample);
		if(listPosInfo !=null && listPosInfo.size() >0){
			logger.info("当前商户下存在pos机，不能删除");
			return "posFail";
		}
		//判断该商户下资金池是否已清空
		FundPoolExample fundPoolExample = new FundPoolExample();
		FundPoolExample.Criteria fundPoolExampleCir = fundPoolExample.createCriteria();
		fundPoolExampleCir.andMERCHANT_CODEEqualTo(merchant_code);
		List<FundPool> listFundPool = this.fundPoolMapper.selectByExample(fundPoolExample);
		if(listFundPool !=null && listFundPool.size() >0){
			BigDecimal CURR_MOENY = listFundPool.get(0).getCURR_MOENY();
			BigDecimal DAY_MOENY = listFundPool.get(0).getDAY_MOENY();
			BigDecimal FROZEN_MOENY = listFundPool.get(0).getFROZEN_MOENY();
			if(CURR_MOENY.compareTo(new BigDecimal("0"))==1 || DAY_MOENY.compareTo(new BigDecimal("0"))==1
					|| FROZEN_MOENY.compareTo(new BigDecimal("0"))==1){
				logger.info("当前商户下资金池有交易数据，不能删除");
				return "fundPoolFail";
			}	
		}
		
		MemberPublicExample memberPublicExample = new MemberPublicExample();
		MemberPublicExample.Criteria memberPublicExampleCir = memberPublicExample.createCriteria();
		memberPublicExampleCir.andMERCHANT_CODEEqualTo(merchant_code);
		//删除商户
		int cnt1 = this.memberPublicMapper.deleteByExample(memberPublicExample);
		//删除商户资金池
		int cnt2 = this.fundPoolMapper.deleteByExample(fundPoolExample);
		//删除商户费率
		MerchantRateExample merchantRateExample = new MerchantRateExample();
		MerchantRateExample.Criteria merchantRateExampleCir = merchantRateExample.createCriteria();
		merchantRateExampleCir.andMERCHANT_CODEEqualTo(merchant_code);
		int cnt3 = this.merchantRateMapper.deleteByExample(merchantRateExample);
		//删除商户登录角色
		UserBaseExample userBaseExample = new UserBaseExample();
		UserBaseExample.Criteria userBaseExampleCir = userBaseExample.createCriteria();
		userBaseExampleCir.andMERCHANT_CODEEqualTo(merchant_code);
		int cnt4 = this.userBaseMapper.deleteByExample(userBaseExample);
		int cnt = cnt1+cnt2+cnt3+cnt4;
		return cnt+"";
	}

	/**
	 * 修改商户信息、商户费率
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly = false, rollbackFor = RuntimeException.class)
	public boolean editMemberPublicByID(MemberPublicWithBLOBs memberPublicWithBLOBs) {
		// 修改商户信息
		this.memberPublicMapper.updateByPrimaryKeySelective(memberPublicWithBLOBs);
		
		//修改商户费率
		BigDecimal bankRate = memberPublicWithBLOBs.getBANK_RATE();//银行卡费率
		BigDecimal wechatRate = memberPublicWithBLOBs.getWECHAT_RATE();//微信费率
		BigDecimal payRate = memberPublicWithBLOBs.getPAY_RATE();//支付宝费率
		BigDecimal membercardRate = memberPublicWithBLOBs.getMEMBERCARD_RATE();//会员卡费率
		BigDecimal walletRate = memberPublicWithBLOBs.getMEMBERCARD_RATE();//钱包费率
		BigDecimal allscoreRate = memberPublicWithBLOBs.getALLSCORE_RATE();//奥斯卡费率
		BigDecimal creditCardRate = memberPublicWithBLOBs.getCREDIT_CARD_RATE();//奥斯卡费率
		String merchantCode = memberPublicWithBLOBs.getMERCHANT_CODE();//会员卡号
		String id = memberPublicWithBLOBs.getID();//
		MerchantRate merchantRate = null;
        //银行卡费率修改
		if(bankRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setRATE(bankRate);
			 merchantRate.setUPDATE_DATE(new Date());
			 
			 MerchantRateExample merchantRateExample = new MerchantRateExample();
			 MerchantRateExample.Criteria merchantRateExampleCir = merchantRateExample.createCriteria();
			 merchantRateExampleCir.andCHN_CODEEqualTo("00");
			 merchantRateExampleCir.andMERCHANT_CODEEqualTo(merchantCode);
			 this.merchantRateMapper.updateByExampleSelective(merchantRate, merchantRateExample);
		}
		//微信费率修改
		if(wechatRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setRATE(wechatRate);
			 merchantRate.setUPDATE_DATE(new Date());
			 
			 MerchantRateExample merchantRateExample = new MerchantRateExample();
			 MerchantRateExample.Criteria merchantRateExampleCir = merchantRateExample.createCriteria();
			 merchantRateExampleCir.andCHN_CODEEqualTo("01");
			 merchantRateExampleCir.andMERCHANT_CODEEqualTo(merchantCode);
			 this.merchantRateMapper.updateByExampleSelective(merchantRate, merchantRateExample);
		}
		//支付宝费率修改
		if(payRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setRATE(payRate);
			 merchantRate.setUPDATE_DATE(new Date());
			 
			 MerchantRateExample merchantRateExample = new MerchantRateExample();
			 MerchantRateExample.Criteria merchantRateExampleCir = merchantRateExample.createCriteria();
			 merchantRateExampleCir.andCHN_CODEEqualTo("02");
			 merchantRateExampleCir.andMERCHANT_CODEEqualTo(merchantCode);
			 this.merchantRateMapper.updateByExampleSelective(merchantRate, merchantRateExample);
		}
		//会员卡费率修改
		if(membercardRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setRATE(membercardRate);
			 merchantRate.setUPDATE_DATE(new Date());
			 
			 MerchantRateExample merchantRateExample = new MerchantRateExample();
			 MerchantRateExample.Criteria merchantRateExampleCir = merchantRateExample.createCriteria();
			 merchantRateExampleCir.andCHN_CODEEqualTo("03");
			 merchantRateExampleCir.andMERCHANT_CODEEqualTo(merchantCode);
			 this.merchantRateMapper.updateByExampleSelective(merchantRate, merchantRateExample);
		}
		//钱包费率修改
		if(walletRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setRATE(walletRate);
			 merchantRate.setUPDATE_DATE(new Date());
			 
			 MerchantRateExample merchantRateExample = new MerchantRateExample();
			 MerchantRateExample.Criteria merchantRateExampleCir = merchantRateExample.createCriteria();
			 merchantRateExampleCir.andCHN_CODEEqualTo("04");
			 merchantRateExampleCir.andMERCHANT_CODEEqualTo(merchantCode);
			 this.merchantRateMapper.updateByExampleSelective(merchantRate, merchantRateExample);
		}
		//奥斯卡费率修改
		if(allscoreRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setRATE(allscoreRate);
			 merchantRate.setUPDATE_DATE(new Date());
			 
			 MerchantRateExample merchantRateExample = new MerchantRateExample();
			 MerchantRateExample.Criteria merchantRateExampleCir = merchantRateExample.createCriteria();
			 merchantRateExampleCir.andCHN_CODEEqualTo("05");
			 merchantRateExampleCir.andMERCHANT_CODEEqualTo(merchantCode);
			 this.merchantRateMapper.updateByExampleSelective(merchantRate, merchantRateExample);
		}
		//信用卡费率修改
				if(allscoreRate.compareTo(new BigDecimal("0")) > -1){
					 merchantRate = new MerchantRate();
					 merchantRate.setRATE(creditCardRate);
					 merchantRate.setUPDATE_DATE(new Date());
					 
					 MerchantRateExample merchantRateExample = new MerchantRateExample();
					 MerchantRateExample.Criteria merchantRateExampleCir = merchantRateExample.createCriteria();
					 merchantRateExampleCir.andCHN_CODEEqualTo("06");
					 merchantRateExampleCir.andMERCHANT_CODEEqualTo(merchantCode);
					 this.merchantRateMapper.updateByExampleSelective(merchantRate, merchantRateExample);
				}
		
		
		return true;
	}
	
	/**
	 * 增加商户信息、商户费率
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly = false, rollbackFor = RuntimeException.class)
	public boolean addMemberPublic(MemberPublicWithBLOBs memberPublicWithBLOBs) {
		// TODO Auto-generated method stub
		memberPublicWithBLOBs.setID(UUID.randomUUID().toString());
		//增加商户信息
		this.memberPublicMapper.insertSelective(memberPublicWithBLOBs);
		//商户费率
		BigDecimal bankRate = memberPublicWithBLOBs.getBANK_RATE();//银行卡费率
		BigDecimal wechatRate = memberPublicWithBLOBs.getWECHAT_RATE();//微信费率
		BigDecimal payRate = memberPublicWithBLOBs.getPAY_RATE();//支付宝费率
		BigDecimal membercardRate = memberPublicWithBLOBs.getMEMBERCARD_RATE();//会员卡费率
		BigDecimal walletRate = memberPublicWithBLOBs.getMEMBERCARD_RATE();//钱包费率
		BigDecimal allscoreRate = memberPublicWithBLOBs.getALLSCORE_RATE();//奥斯卡费率
		BigDecimal creditCardRate = memberPublicWithBLOBs.getCREDIT_CARD_RATE();//信用卡费率
		String merchantCode = memberPublicWithBLOBs.getMERCHANT_CODE();//商户编号
		//增加资金池
		FundPool fundPool = new FundPool();
		fundPool.setID(UUID.randomUUID().toString());
		fundPool.setMERCHANT_CODE(merchantCode);
		fundPool.setCURR_MOENY(new BigDecimal("0"));
		fundPool.setDAY_MOENY(new BigDecimal("0"));
		fundPool.setFROZEN_MOENY(new BigDecimal("0"));
		fundPool.setVALID_MOENY(new BigDecimal("0"));
		fundPool.setCREATE_TIME(new Date());
		this.fundPoolMapper.insertSelective(fundPool);
		
		MerchantRate merchantRate = null;
		//银行卡费率增加
		if(bankRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setID(UUID.randomUUID().toString());
			 merchantRate.setMERCHANT_CODE(merchantCode);
			 merchantRate.setCHN_CODE("00");
			 merchantRate.setRATE(bankRate);
			 merchantRate.setCREATE_DATE(new Date());
			 this.merchantRateMapper.insertSelective(merchantRate);
		}
		//微信费率增加
		if(wechatRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setID(UUID.randomUUID().toString());
			 merchantRate.setMERCHANT_CODE(merchantCode);
			 merchantRate.setCHN_CODE("01");
			 merchantRate.setRATE(wechatRate);
			 merchantRate.setCREATE_DATE(new Date());
			 this.merchantRateMapper.insertSelective(merchantRate);
		}
		//支付宝费率增加
		if(payRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setID(UUID.randomUUID().toString());
			 merchantRate.setMERCHANT_CODE(merchantCode);
			 merchantRate.setCHN_CODE("02");
			 merchantRate.setRATE(payRate);
			 merchantRate.setCREATE_DATE(new Date());
			 this.merchantRateMapper.insertSelective(merchantRate);
		}
		//会员卡费率增加
		if(membercardRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setID(UUID.randomUUID().toString());
			 merchantRate.setMERCHANT_CODE(merchantCode);
			 merchantRate.setCHN_CODE("03");
			 merchantRate.setRATE(membercardRate);
			 merchantRate.setCREATE_DATE(new Date());
			 this.merchantRateMapper.insertSelective(merchantRate);
		}
		//钱包费率增加
		if(walletRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setID(UUID.randomUUID().toString());
			 merchantRate.setMERCHANT_CODE(merchantCode);
			 merchantRate.setCHN_CODE("04");
			 merchantRate.setRATE(walletRate);
			 merchantRate.setCREATE_DATE(new Date());
			 this.merchantRateMapper.insertSelective(merchantRate);
		}
		//奥斯卡费率增加
		if(allscoreRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setID(UUID.randomUUID().toString());
			 merchantRate.setMERCHANT_CODE(merchantCode);
			 merchantRate.setCHN_CODE("05");
			 merchantRate.setRATE(allscoreRate);
			 merchantRate.setCREATE_DATE(new Date());
			 this.merchantRateMapper.insertSelective(merchantRate);
		}
		//奥斯卡费率增加
		if(allscoreRate.compareTo(new BigDecimal("0")) > -1){
			 merchantRate = new MerchantRate();
			 merchantRate.setID(UUID.randomUUID().toString());
			 merchantRate.setMERCHANT_CODE(merchantCode);
			 merchantRate.setCHN_CODE("06");
			 merchantRate.setRATE(creditCardRate);
			 merchantRate.setCREATE_DATE(new Date());
			 this.merchantRateMapper.insertSelective(merchantRate);
		}
		return true;
	}


	public String randomMath() {
        int cnt;
        String str;
		do{
		  str = this.memberPublicMapper.randomMath();
          cnt = this.memberPublicMapper.checkRandomMath(str);
        }while(cnt!=0);
		return str;
	}
	
	/**
	 * 生成商户编号
	 */
	public String getMerchantCode() {
		String str = this.memberPublicMapper.maxMerchantCode();
		//取得最大商户编号截取后4位+1
		str=str.substring(2);
		
		BigDecimal abc=new BigDecimal(str);
		BigDecimal a=abc.add(new BigDecimal("1"));
		String merchantCode ="88" + (a.toString());
		
		return merchantCode;
		
	}

	public Map getMsgTemplateByMap(Pager pager, Map map) {
		
		String merchant_code = (String)map.get("merchant_code");
		MsgTemplateExample msgTemplateExample = new MsgTemplateExample();
		MsgTemplateExample.Criteria msgTemplateExampleCir = msgTemplateExample.createCriteria();
		
		if(merchant_code!=null && !"".equals(merchant_code)){
			msgTemplateExampleCir.andMERCHANT_CODELike("%"+merchant_code+"%");
			
		}
		
		int total = msgTemplateMapper.countByExample(msgTemplateExample);
		pager.setTotal(total);
		if(pager.getSize()>=0 && pager.getOffest()>=0){
			msgTemplateExample.setLimitStart(pager.getOffest());
			msgTemplateExample.setLimitEnd(pager.getPage()*pager.getSize());
		}
		msgTemplateExample.setOrderByClause("create_time desc");
		List<MsgTemplate> list = this.msgTemplateMapper.selectByExample(msgTemplateExample);
		
		Map m = new HashMap();
		m.put("total", total);
		m.put("list", list);
		return m;
	}

	public int addMsgTemplateByMap(Map map) throws Exception {
		String merchant_code = (String) map.get("merchant_code");
		String public_id = (String)map.get("public_id");
		String template_id = (String)map.get("template_id");
		String template_type = (String)map.get("template_type");
		String remark = (String)map.get("remark");
		String status = (String)map.get("status");
		MsgTemplate msgTemplate = new MsgTemplate();
		
		msgTemplate.setID(UUID.randomUUID().toString());
		msgTemplate.setCREATE_TIME(new Date());
		if(merchant_code!=null && !"".equals(merchant_code)){
			msgTemplate.setMERCHANT_CODE(merchant_code.trim());
		}
		if(public_id!=null && !"".equals(public_id)){
			msgTemplate.setPUBLIC_ID(public_id.trim());
		}
		if(template_id!=null && !"".equals(template_id)){
			msgTemplate.setTEMPLATE_ID(template_id.trim());
		}
		if(template_type!=null && !"".equals(template_type)){
			msgTemplate.setTEMPLATE_TPYE(template_type.trim());
		}
		if(remark!=null && !"".equals(remark)){
			remark = new String(remark.getBytes("ISO-8859-1"),"UTF-8");
			
			msgTemplate.setREMARK(remark.trim());
			 
		}
		if(status!=null && !"".equals(status)){
			msgTemplate.setSTATUS(status.trim());
		}
		int cnt = this.msgTemplateMapper.insertSelective(msgTemplate);
		return cnt;
		
	}

	public int delMsgTemplateByID(String id) throws Exception {
		int cnt = this.msgTemplateMapper.deleteByPrimaryKey(id);
		return cnt;
	}


}
