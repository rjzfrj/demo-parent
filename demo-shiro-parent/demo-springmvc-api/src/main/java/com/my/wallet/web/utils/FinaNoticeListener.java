package com.my.wallet.web.utils;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.wallet.web.constant.ConstantCode;
import com.my.wallet.web.domain.WalletBindCard;
import com.rabbitmq.client.Channel;

public class FinaNoticeListener implements MessageListener {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(FinaNoticeListener.class);


	@Autowired
	private ConnectionFactory connectionFactory;

	public void onMessage(Message message) {
		logger.info("-----------------------接收通知消息开始-------------------------------");
		logger.info("message = " + message.getBody());
		boolean isno=false;
		String msg;
		try {
			msg = new String(message.getBody(), ContentTypes.UTF8_ENCODING);

			JSONObject jData = JSON.parseObject(msg);
			logger.info("msg = " + msg);
//
//			if (!jData.getString("sysId").equals(PropertyUtil.getProperty("SYS_ID"))) {
//				logger.error("sysId not match. sysId = "+ jData.getString("sysId"));
//				return;
//			}
//
//			logger.info("FinaNoticeListener----onMessage---tranNo========>>>>>>>>>>"+ jData.getString("tranNo"));
//			String tranCode=jData.getString("tranCode");	//交易类型
//			logger.info("FinaNoticeListener----onMessage---tranCode========>>>>>>>>>>"+ jData.getString("tranCode"));
//			if("0701".equals(tranCode)){	//绑卡业务接收通知
//				logger.info("快捷支付绑卡收到通知： tranNo:"+jData.getString("tranNo")+"tranStatus"+jData.getString("tranStatus"));
//				WalletBindCard bindCard = new WalletBindCard();
//				bindCard.setTranNo(jData.getString("tranNo")); // 流水号
//				if (jData.getString("remark") != null) {
//					bindCard.setErrorCause(jData.getString("remark"));
//				}
//				// 通知中状态为01表示绑卡成功（和同步不一样，同步是02）
//				if (ConstantCode.C_BIND_CARD_TRANSTATUS_01.equals(jData.getString("tranStatus"))) {
//					bindCard.setTranStatus(ConstantCode.C_BIND_CARD_TRANSTATUS_02);
//				} else {
//					bindCard.setTranStatus(ConstantCode.C_BIND_CARD_TRANSTATUS_03);
//				}
//				logger.info("-----------------------绑卡业务接收通知服务处理数据开始-------------------------------");
//				//callBackService.notifyBindCardStatus(bindCard);
//				logger.info("-----------------------绑卡业务接收通知服务处理数据结束-------------------------------");
//			
//			}else if("0501".equals(tranCode)){  //代付逻辑
//				logger.info("代付逻辑通知服务处理数据开始：tranStatus"+jData.getString("tranStatus")+" tranNo:"+jData.getString("tranNo"));
//			//	isno=callBackService.personalWithDrawStatus(jData.getString("tranStatus"), jData.getString("tranNo"),jData.getString("remark"));
//				logger.info("-----------------------代付逻辑通知服务处理数据结束执行成功状态"+isno+"-------------------------------");
//				//商户提现逻辑执行下面的
//				//boolean ok=shopBusinessService.updateWithDrawStatus(jData.getString("tranStatus"), jData.getString("tranNo"),jData.getString("remark"));
//				logger.info("如果是商户提现成功这个会执行成功");
//			}else if("0201".equals(tranCode)){  //快捷支付收到通知
//				logger.info("充值逻辑通知服务处理数据开始：tranStatus"+jData.getString("tranStatus")+" tranNo:"+jData.getString("tranNo"));
//				//callBackService.rechargeCallback(jData.getString("tranStatus"), jData.getString("tranNo"),jData.getString("remark"));
//			}
//			logger.info("-----------------------执行回执开始---------------------------------------");
//			// 添加回执
//		
//				HashMap<String, String> map = new HashMap<String, String>();
//				map.put("sysId", jData.getString("sysId"));
//				map.put("tranNo", jData.getString("tranNo"));
//				String ret = JSON.toJSONString(map);
//				reCallMessage(ret);
//			logger.info("-----------------------执行回执结束---------------------------------------");
		} catch (Exception e) {
			logger.info("接受通知监听分发任务处理失败了"+e.getMessage());
		}
		logger.info("-----------------------接收通知消息结束-------------------------------");
	}

	// 回执消息
	private void reCallMessage(String message) {
		Channel channel = null;
		Connection conn = null;
		try {
			conn = connectionFactory.createConnection();
			channel = conn.createChannel(false);
			channel.basicPublish("", "FINANCE_TRANS_REPLY", null,message.getBytes());
		} catch (Exception e) {
			logger.info("-----------------------执行回执开始---------------------------------------");
			e.printStackTrace();
		} finally {
			try {
				channel.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
