package com.allscore.wireless.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.wireless.biz.CheckBusinessManager;
import com.allscore.wireless.biz.CheckErrorManager;
import com.allscore.wireless.biz.MemberPublicManager;
import com.allscore.wireless.biz.UsersBusinessManager;
import com.allscore.wireless.dao.CheckBusiness;
import com.allscore.wireless.dao.CheckBusinessMapper;
import com.allscore.wireless.dao.CheckError;
import com.allscore.wireless.dao.MemberPublic;
import com.allscore.wireless.dao.UsersBussiness;


public class SellerCheckBillQuartzTask {
	
	private String filePath;
	
	@Autowired
	UsersBusinessManager usersBusinessManager;

	@Autowired
	CheckBusinessManager checkBusinessManager;
	
	@Autowired
	CheckErrorManager checkErrorManager;
	
	@Autowired
	MemberPublicManager memberPublicManager;
	
	public void execute() throws Exception{
        FileReader fr = new FileReader(filePath);
		//InputStreamReader fr= new InputStreamReader(new FileInputStream(new File("d:/32_20150612.txt")));
		BufferedReader br = new BufferedReader(fr);
		List listFile = new ArrayList();
		String ss="";
		while((ss=br.readLine())!=null){
			String[] str = ss.split("\\|",100);
			listFile.add(str);
		}
		System.out.println("读取文件数："+listFile.size());
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String businessTime = sdf.format(date);
		//获取上一日期的交易数据与对账文件比对
		List<UsersBussiness> listXf = this.usersBusinessManager.getUsersBussinessType(businessTime);

 
        System.out.println("消费记录数："+listXf.size());
		int fileSize = listFile.size();//对账文件信息
		int baseSize = listXf.size();//数据库记录信息
		for(int i=0;i<fileSize;i++){
			//取出对账文件中每笔交易
			String[] str = (String[]) listFile.get(i);
			String settlement_dateFile = str[0];//清算日期
			String MERCHANT_CODEFile = str[1];//商户编号
			String client_idFile = str[2];//终端号
			String mesTypeFile = str[3];//消息类型
			String manageCodeFile = str[4];//处理码
			String codeFile = str[5];//卡号
			String busi_amtFile = str[6];//交易金额
			String serialFile = str[7];//交易跟踪号
			String poundageFile = str[16];//商户手续费
			for(int j=0;j<baseSize;j++){
				String MERCHANT_CODE = listXf.get(j).getMERCHANT_CODE();//商户编号
				String business_source = listXf.get(j).getBUSINESS_SOURCE();//交易来源
				BigDecimal order_amt = listXf.get(j).getORDER_AMT();//交易金额
				String serial = listXf.get(j).getSERIAL();//流水号
				BigDecimal poundage = listXf.get(j).getPOUNDAGE();//手续费
				String business_time = listXf.get(j).getBUSINESS_TIME().substring(0, 10);//交易时间
				
				boolean xfFile = ("0200".equals(mesTypeFile) || "0210".equals(mesTypeFile))
				&& "000000".equals(manageCodeFile);//对账消费
				boolean xf = "00".equals(business_source);//消费
				//消费记录对账
				if(xfFile && xf){
					//双方系统都有
					
					if(MERCHANT_CODEFile.equals(MERCHANT_CODE) && serialFile.equals(serial) && settlement_dateFile.equals(business_time)){
						CheckBusiness checkBusiness = new CheckBusiness(); 
						//比对交易金额是否相等
						if(order_amt.compareTo(new BigDecimal(busi_amtFile))==0){
							//写入交易对账表
							checkBusiness.setID(UUID.randomUUID().toString());
							checkBusiness.setMERCHANT_CODE(MERCHANT_CODE);
							checkBusiness.setUSER_ID(listXf.get(j).getUSER_ID());
							checkBusiness.setBUSINESS_TYPE(listXf.get(j).getBUSINESS_TYPE());
							checkBusiness.setBUSINESS_CODE(listXf.get(j).getBUSINESS_SOURCE());
							checkBusiness.setCARD_NUMBER(listXf.get(j).getCARD_NUMBER());
							checkBusiness.setTRAN_AMT(listXf.get(j).getTRAN_AMT());
							checkBusiness.setPOUNDAGE(listXf.get(j).getPOUNDAGE());
							checkBusiness.setORDER_AMT(listXf.get(j).getORDER_AMT());
							checkBusiness.setSERIAL(listXf.get(j).getSERIAL());
							checkBusiness.setSETTLEMENT_DATE(business_time);
							SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							checkBusiness.setCREATE_TIME(sdf1.format(new Date()));
							checkBusiness.setUPDATE_TIME(new Date());
							checkBusiness.setCHECK_RESULT("00");
							this.checkBusinessManager.addCheckBusinessByList(checkBusiness);
							
						}
						else{
							CheckError checkError = new CheckError();
							//写入差错记录表--错账：双方系统都有，金额不一致--对账文件记录
							checkError.setID(UUID.randomUUID().toString());
							checkError.setMERCHANT_CODE(MERCHANT_CODE);
							checkError.setUSER_ID(listXf.get(j).getUSER_ID());
							checkError.setCARD_NUMBER(listXf.get(j).getCARD_NUMBER());
							checkError.setTRAN_AMT(new BigDecimal(busi_amtFile));
							checkError.setPOUNDAGE(new BigDecimal(poundageFile));
							checkError.setORDER_AMT(listXf.get(j).getORDER_AMT());
							checkError.setSERIAL(listXf.get(j).getSERIAL());
							checkError.setCHECK_RESULT("02");//错账
							checkError.setHANDLE_RESULT("00");
							SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							checkError.setCREATE_TIME(sdf1.format(new Date()));
							checkError.setUPDATE_TIME(sdf1.format(new Date()));
							checkError.setSETTLEMENT_DATE(business_time);
							checkError.setBUSINESS_TYPE(listXf.get(j).getBUSINESS_TYPE());
							checkError.setMERCHANT_NAME(listXf.get(j).getMERCHANT_NAME());
							checkError.setCLIENT_ID(client_idFile);
							this.checkErrorManager.addCheckErrorByList(checkError);
							
							CheckError checkError1 = new CheckError();
							//写入差错记录表--错账：双方系统都有，金额不一致--系统记录
							checkError1.setID(UUID.randomUUID().toString());
							checkError1.setMERCHANT_CODE(MERCHANT_CODE);
							checkError1.setUSER_ID(listXf.get(j).getUSER_ID());
							checkError1.setCARD_NUMBER(listXf.get(j).getCARD_NUMBER());
							checkError1.setTRAN_AMT(listXf.get(j).getTRAN_AMT());
							checkError1.setPOUNDAGE(listXf.get(j).getPOUNDAGE());
							checkError1.setORDER_AMT(listXf.get(j).getORDER_AMT());
							checkError1.setSERIAL(listXf.get(j).getSERIAL());
							checkError1.setCHECK_RESULT("02");//错账
							checkError1.setHANDLE_RESULT("00");
							checkError1.setCREATE_TIME(sdf1.format(new Date()));
							checkError1.setUPDATE_TIME(sdf1.format(new Date()));
							checkError1.setSETTLEMENT_DATE(business_time);
							checkError1.setBUSINESS_TYPE(listXf.get(j).getBUSINESS_TYPE());
							checkError1.setMERCHANT_NAME(listXf.get(j).getMERCHANT_NAME());
							checkError1.setCLIENT_ID(client_idFile);
							this.checkErrorManager.addCheckErrorByList(checkError1);
						}
					}else{
						if(j == baseSize-1){
							CheckError checkError = new CheckError();
							//写入差错记录表--短款：系统无记录，对账文件有记录
							checkError.setID(UUID.randomUUID().toString());
							checkError.setMERCHANT_CODE(MERCHANT_CODE);
							checkError.setUSER_ID(listXf.get(j).getUSER_ID());
							checkError.setCARD_NUMBER(codeFile);
							checkError.setTRAN_AMT(new BigDecimal(busi_amtFile));
							checkError.setPOUNDAGE(new BigDecimal(poundageFile));
							//checkError.setORDER_AMT(listXf.get(j).getORDER_AMT());
							checkError.setSERIAL(serialFile);
							checkError.setCHECK_RESULT("01");//短款
							checkError.setHANDLE_RESULT("00");
							SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							checkError.setCREATE_TIME(sdf1.format(new Date()));
							checkError.setUPDATE_TIME(sdf1.format(new Date()));
							checkError.setSETTLEMENT_DATE(business_time);
							checkError.setBUSINESS_TYPE("00");
							MemberPublic memberPublic = memberPublicManager.getMemberPublicByMerchantID(MERCHANT_CODE);
							
							checkError.setMERCHANT_NAME(memberPublic.getMERCHANT_NAME());
							checkError.setCLIENT_ID(client_idFile);
							this.checkErrorManager.addCheckErrorByList(checkError);	
						}
	
					}
					
				}
				

			}
		}
		
		
		for(int j=0;j<baseSize;j++){
			String MERCHANT_CODE = listXf.get(j).getMERCHANT_CODE();//商户编号
			String business_source = listXf.get(j).getBUSINESS_SOURCE();//交易来源
			BigDecimal order_amt = listXf.get(j).getORDER_AMT();//交易金额
			BigDecimal tran_amt = listXf.get(j).getTRAN_AMT();//金额
			String serial = listXf.get(j).getSERIAL();//流水号
			BigDecimal poundage = listXf.get(j).getPOUNDAGE();//手续费
			String business_time = listXf.get(j).getBUSINESS_TIME().substring(0, 10);//交易时间
			for(int i=0;i<fileSize;i++){
				//取出对账文件中每笔交易
				String[] str = (String[]) listFile.get(i);
				String settlement_dateFile = str[0];//清算日期
				String MERCHANT_CODEFile = str[1];//商户编号
				String client_idFile = str[2];//终端号
				String mesTypeFile = str[3];//消息类型
				String manageCodeFile = str[4];//处理码
				String codeFile = str[5];//卡号
				String busi_amtFile = str[6];//交易金额
				String serialFile = str[7];//交易跟踪号
				String poundageFile = str[16];//商户手续费
				
				boolean xfFile = ("0200".equals(mesTypeFile) || "0210".equals(mesTypeFile))
				&& "000000".equals(manageCodeFile);//对账消费
				boolean xf = "00".equals(business_source);//消费
				//消费记录对账
				if(xfFile && xf){
					//双方系统都有
					CheckError checkError = new CheckError();
					if(MERCHANT_CODEFile.equals(MERCHANT_CODE) && serialFile.equals(serial) && settlement_dateFile.equals(business_time)){
						
						
					}else{
						if(i == fileSize-1){
							//写入差错记录表--长款：系统无记录，对账文件有记录
							checkError.setID(UUID.randomUUID().toString());
							checkError.setMERCHANT_CODE(MERCHANT_CODE);
							checkError.setUSER_ID(listXf.get(j).getUSER_ID());
							checkError.setCARD_NUMBER(listXf.get(j).getCARD_NUMBER());
							checkError.setTRAN_AMT(tran_amt);
							checkError.setPOUNDAGE(poundage);
							checkError.setORDER_AMT(order_amt);
							checkError.setSERIAL(serial);
							checkError.setCHECK_RESULT("00");//长款
							checkError.setHANDLE_RESULT("00");
							SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							checkError.setCREATE_TIME(sdf1.format(new Date()));
							checkError.setUPDATE_TIME(sdf1.format(new Date()));
							checkError.setSETTLEMENT_DATE(business_time);
							checkError.setBUSINESS_TYPE("00");
							checkError.setMERCHANT_NAME(listXf.get(j).getMERCHANT_NAME());
							//checkError.setCLIENT_ID(client_idFile);
							this.checkErrorManager.addCheckErrorByList(checkError);	
						}
	
					}
					
				}
				

			}
		}
		
    }


	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
