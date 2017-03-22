package com.my.wallet.web.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.my.wallet.web.dao.WalletPayOrderMapper;
import com.my.wallet.web.domain.WalletPayOrder;
import com.my.wallet.web.security.RSACoder;

public class NotifyTask
{
	private static final Logger logger = Logger.getLogger(NotifyTask.class);
	public void run()
	{
		String result = "";
	}

	/**
	 * 发送回调信息给回调地址
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sendRequest(String url, Map<String, String> params)
	{
		PostMethod post = new PostMethod(url);
		try
		{
			post.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			Set<String> keySet = params.keySet();
			for (String key : keySet)
			{
				if (null != key && null != params.get(key))
					post.addParameter(new NameValuePair(key, params.get(key)));
			}
			// HttpUtil.postForm(url, params);
			HttpClient httpclient = new HttpClient();
			httpclient.executeMethod(post);
			String response = post.getResponseBodyAsString();
			logger.info("========== NotifyTask : run() : response =============> " + response);
			return response;
		} catch (Exception ex)
		{
			return "FAIL";
		} finally
		{
			post.releaseConnection();
		}
	}

	// /**
	// * 发送回调信息给回调地址
	// * @param url
	// * @param params
	// * @return
	// */
	// public static String sendRequest(String url, Map<String, String> params) {
	// PostMethod post = new PostMethod(url);
	// try {
	// post.setRequestHeader("Content-Type",
	// "application/x-www-form-urlencoded;charset=utf-8");
	// Set<String> keySet = params.keySet();
	// for (String key : keySet) {
	// if (null != key && null != params.get(key))
	// post.addParameter(new NameValuePair(key, params.get(key)));
	// }
	// HttpClient httpclient = new HttpClient();
	// httpclient.executeMethod(post);
	// String response = post.getResponseBodyAsString();
	// logger.info("========== NotifyTask : run() : response =============> " + response);
	// return response;
	// } catch (Exception ex) {
	// return "FAIL";
	// } finally {
	// post.releaseConnection();
	// }
	// }

	/**
	 * 签名方法
	 * 
	 * @param sdkPayOrder
	 * @return
	 */
	public String getSign(Map<String, String> params) throws Exception
	{

		// 数据源
		String source = RSACoder.createLinkString(params);
		logger.info("-------NotifyTask-------sign source--------------:" + source);

		// ****** RSA 公私钥 ****** //
		Map<String, String> keyMap = RSACoder.getKeys();
		//String selfPrivateKey = keyMap.get("privateKey");
		String selfPrivateKey =null;
		try
		{
		// 调用校验接口
			logger.info("商户安全等级");
			//salerInfo.getSelfPublicKey()
			selfPrivateKey="";
		} catch (Exception e)
		{
			logger.info("-------通知前获取selfPrivateKey:" + e.getMessage());
		}
		String sign = RSACoder.sign(source, selfPrivateKey);
		logger.info("-------NotifyTask-------sign---:" + sign);

		return sign;
	}

	public static void main(String[] args) throws Exception
	{
		Map<String, String> params = new HashMap<String, String>();
		String url = "http://192.168.9.20:8080/wallet/api/home";
		// String jsonParam =
		// "{\"method\":\"receive.syxdata\", \"sign\":\"P8TNAVXYelFnZEsUbYzkeO74yryMcj4lpA6kZ+ul4n9BpSRcOOE9/FHvq/LDucSmX/VSDLKEk1SNwP9/8rNOWoKeCVrkx3arAEgRwiW2AKQNRVzADQQzCNDRs6AbNqFWKC2MIadqPpimjUeJAV2k0t8wB+ficp/BGgKkwa7QRnE=\", \"params\":{\"merchantId\":\"1015013101118\", \"notify\":\"http://beta.touch.laiyifen.cn/rpc.php\", \"outOrderId\":\"130414562123652\", \"outOrderTime\":\"20150625180339893\", \"transAmt\":\"1.00\", \"userNo\":\"13942652325\"}}";
		//
		// params.put("method", "receive.syxdata");
		params.put("merchantId", "001015013101118");
		params.put("notifyUrl", "http://192.168.9.20:8080/wallet/api/home");
		params.put("outOrderId", "130414562123652");
		params.put("outOrderTime", "20150625180339893");
		params.put("transAmt", "1.00");
		params.put("userNo", "1986");
		// JSONObject json = JSONObject.fromObject(jsonParam);
		// logger.info(json.getString("method"));
		// String strJson = json.getString("params");
		// JSONObject json1 = JSONObject.fromObject(strJson);
		// logger.info(json1.getString("outOrderId"));
		// String jsonParam =
		// "{\"method\":\"receive.syxdata\",\"merchantId\":\"1015013101118\",\"notify\":\"http://beta.touch.laiyifen.cn/rpc.php\",\"outOrderId\":\"130414562123652\",\"outOrderTime\":\"20150625180339893\",\"transAmt\":\"1.00\",\"userNo\":\"13942652325\"}";
		String data = RSACoder.createLinkString(params);
		logger.info("sign source:" + data);
		String privateKey = RSACoder.getKeys().get("privateKey");
		String sign = RSACoder.sign(data, privateKey);
		logger.info("sign:" + sign);
		params.put("sign", sign);
		// String result = post(url, jsonParam);
		String result = sendRequest(url, params);
		// String result = sendRequestForPost(url, params);
		logger.info("result:" + result);

		/*** -------------------------------------------- **/

	}

	public static String sendRequestForPost(String url, Map<String, String> params) throws Exception
	{
		String retStr = "";
		PostMethod post = null;
		InputStream in = null;
		BufferedReader reader = null;
		try
		{
			HttpClient hc = new HttpClient();
			post = new PostMethod(url);
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			setParameter(post, params);
			hc.executeMethod(post);
			if (post.getStatusCode() == HttpStatus.SC_OK)
			{
				logger.info(post.getResponseBodyAsString());

				in = post.getResponseBodyAsStream();
				if (in != null)
				{
					reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
					if (reader != null)
					{
						String line = null;
						StringBuffer sb = new StringBuffer();
						while ((line = reader.readLine()) != null)
						{
							sb.append("\n" + line);
						}
						retStr = sb.toString();
					}
				}
			}
			hc = null;
		} catch (Exception e)
		{
			throw new Exception(e);
		} finally
		{
			try
			{
				if (in != null)
				{
					in.close();
				}
				if (reader != null)
				{
					reader.close();
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			} finally
			{
				if (post != null)
					post.releaseConnection();
			}
		}
		return retStr;
	}

	/**
	 * 组装Parameter语句
	 * 
	 * @param orderby
	 * @return
	 */
	private static void setParameter(PostMethod post, Map<String, String> params)
	{
		if (params != null && params.size() > 0)
		{
			for (String key : params.keySet())
			{
				post.setParameter(key, params.get(key) + "");
			}
		}
	}

}
