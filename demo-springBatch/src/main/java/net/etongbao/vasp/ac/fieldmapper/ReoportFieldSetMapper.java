package net.etongbao.vasp.ac.fieldmapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.etongbao.vasp.ac.pojo.Ledger;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;


/**
 * <p>
 * Project:					demo-springBatch
 * <p>
 * Module ID:				<模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments:				<文件转实体字段映射类>
 * <p>
 * JDK version used:		JDK1.7
 * <p>
 * NameSpace:				net.etongbao.vasp.ac.fieldmapper.ReoportFieldSetMapper.java
 * <p>
 * Author:					liuzf
 * <p>
 * Create Date:				2017年7月13日
 * <p>
 * Modified By:				<修改人中文名或拼音缩写>
 * <p>
 * Modified Date:			<修改日期>
 * <p>
 * Why & What is modified:	<修改原因描述>
 * <p>
 * Version:					v1.0
*/ 
public class ReoportFieldSetMapper implements FieldSetMapper<Ledger> {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
	@Override
	public Ledger mapFieldSet(FieldSet fieldSet) throws BindException {
		Ledger leader=new Ledger();
		//receiptDate,memberName,checkNumber,checkDate,paymentType,depositAmount,paymentAmount,comments
		String date = fieldSet.readString("receiptDate");
		String checkDate = fieldSet.readString("checkDate");
		try {
			leader.setReceiptDate(dateFormat.parse(date));
			leader.setCheckDate(dateFormat.parse(checkDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		leader.setMemberName(fieldSet.readString("memberName"));
		leader.setCheckNumber(fieldSet.readString("checkNumber"));
		leader.setPaymentType(fieldSet.readString("paymentType"));
		String depositAmount=fieldSet.readString("depositAmount");
		String paymentAmount=fieldSet.readString("paymentAmount");
		depositAmount=depositAmount.replace("$", "");
		paymentAmount=paymentAmount.replace("$", "");
		leader.setDepositAmount(Double.valueOf(depositAmount));
		leader.setPaymentAmount(Double.valueOf(paymentAmount));
		leader.setComments(fieldSet.readString("comments"));
		return leader;
	}
	
	public static void main(String[] args) {
		try {
			SimpleDateFormat dateFormataa = new SimpleDateFormat("yy/MM/dd");
			Date date=dateFormataa.parse("22/22/15");
			System.out.println(date.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
