package com.my.wallet.web.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Vector;
/**  
 * HTTP请求对象  
 *   
 * @author hetong  
 */  
public class HttpRequester {   
    private String defaultContentEncoding;
    
    public HttpRequester() {   
        this.defaultContentEncoding = Charset.defaultCharset().name(); 
    }   
    
    /**  
     * 发送GET请求  
     * @param urlString  URL地址  
     * @return 响应对象  
     * @throws IOException  
     */  
    public HttpRespons sendGet(String urlString) throws IOException {   
        return this.send(urlString, "GET", null, null);   
    }   
    
    /**  
     * 发送GET请求  
     * @param urlString  URL地址  
     * @param params  参数集合  
     * @return 响应对象  
     * @throws IOException  
     */  
    public HttpRespons sendGet(String urlString, Map<String, String> params)   
            throws IOException {   
        return this.send(urlString, "GET", params, null);   
    }   
    
    /**  
     * 发送GET请求  
     * @param urlString  URL地址  
     * @param params  参数集合  
     * @param propertys  请求属性  
     * @return 响应对象  
     * @throws IOException  
     */  
    public HttpRespons sendGet(String urlString, Map<String, String> params,   
            Map<String, String> propertys) throws IOException {   
        return this.send(urlString, "GET", params, propertys);   
    }   
    
    /**  
     * 发送POST请求  
     * @param urlString  URL地址  
     * @return 响应对象  
     * @throws IOException  
     */  
    public HttpRespons sendPost(String urlString) throws IOException {   
        return this.send(urlString, "POST", null, null);   
    }   
    
    /**  
     * 发送POST请求  
     * @param urlString  URL地址  
     * @param params  参数集合  
     * @return 响应对象  
     * @throws IOException  
     */  
    public HttpRespons sendPost(String urlString, Map<String, String> params)   
            throws IOException {   
        return this.send(urlString, "POST", params, null);   
    }   
    
    /**  
     * 发送POST请求  
     * @param urlString  URL地址  
     * @param params  参数集合  
     * @param propertys 请求属性  
     * @return 响应对象  
     * @throws IOException  
     */  
    public HttpRespons sendPost(String urlString, Map<String, String> params,   
            Map<String, String> propertys) throws IOException {   
        return this.send(urlString, "POST", params, propertys);   
    }   
    
    /**  
     * 发送HTTP请求  
     * @param urlString  
     * @return 响映对象  
     * @throws IOException  
     */  
    private HttpRespons send(String urlString, String method,   
            Map<String, String> parameters, Map<String, String> propertys)   
            throws IOException {   
        HttpURLConnection urlConnection = null;   
    
        if (method.equalsIgnoreCase("GET") && parameters != null) {   
            StringBuffer param = new StringBuffer();   
            int i = 0;   
            for (String key : parameters.keySet()) {   
                if (i == 0)   
                    param.append("?");   
                else  
                    param.append("&");   
                param.append(key).append("=").append(parameters.get(key));   
                i++;   
            }   
            urlString += param;   
        }   
        URL url = new URL(urlString);   
        urlConnection = (HttpURLConnection) url.openConnection();   
    
        urlConnection.setRequestMethod(method);   
        urlConnection.setDoOutput(true);   
        urlConnection.setDoInput(true);   
        urlConnection.setUseCaches(false);
        urlConnection.setConnectTimeout(50000);
    
        if (propertys != null)   
            for (String key : propertys.keySet()) {   
                urlConnection.addRequestProperty(key, propertys.get(key));   
            }   
    
        if (method.equalsIgnoreCase("POST") && parameters != null) {   
            StringBuffer param = new StringBuffer();   
            for (String key : parameters.keySet()) {   
                param.append("&");   
                param.append(key).append("=").append(parameters.get(key));   
            }   
            urlConnection.getOutputStream().write(param.toString().getBytes());   
            urlConnection.getOutputStream().flush();   
            urlConnection.getOutputStream().close();   
        }   
    
        return this.makeContent(urlString, urlConnection);   
    }   
    
    /**  
     * 得到响应对象  
     * @param urlConnection  
     * @return 响应对象  
     * @throws IOException  
     */  
    private HttpRespons makeContent(String urlString,   
            HttpURLConnection urlConnection) throws IOException {   
        HttpRespons httpResponser = new HttpRespons();   
        try {   
            InputStream in = urlConnection.getInputStream();   
            BufferedReader bufferedReader = new BufferedReader(   
                    new InputStreamReader(in));   
            httpResponser.contentCollection = new Vector<String>();   
            StringBuffer temp = new StringBuffer();   
            String line = bufferedReader.readLine();   
            while (line != null) {   
                httpResponser.contentCollection.add(line);   
                temp.append(line).append("\r\n");   
                line = bufferedReader.readLine();   
            }   
            bufferedReader.close();   
    
            String ecod = urlConnection.getContentEncoding();   
            if (ecod == null)   
                ecod = this.defaultContentEncoding;   
    
            httpResponser.urlString = urlString;   
    
            httpResponser.defaultPort = urlConnection.getURL().getDefaultPort();   
            httpResponser.file = urlConnection.getURL().getFile();   
            httpResponser.host = urlConnection.getURL().getHost();   
            httpResponser.path = urlConnection.getURL().getPath();   
            httpResponser.port = urlConnection.getURL().getPort();   
            httpResponser.protocol = urlConnection.getURL().getProtocol();   
            httpResponser.query = urlConnection.getURL().getQuery();   
            httpResponser.ref = urlConnection.getURL().getRef();   
            httpResponser.userInfo = urlConnection.getURL().getUserInfo();   
    
            httpResponser.content = new String(temp.toString().getBytes(), ecod);   
            httpResponser.contentEncoding = ecod;   
            httpResponser.code = urlConnection.getResponseCode();   
            httpResponser.message = urlConnection.getResponseMessage();   
            httpResponser.contentType = urlConnection.getContentType();   
            httpResponser.method = urlConnection.getRequestMethod();   
            httpResponser.connectTimeout = urlConnection.getConnectTimeout();   
            httpResponser.readTimeout = urlConnection.getReadTimeout();   
    
            return httpResponser;   
        } catch (IOException e) {   
            throw e;   
        } finally {   
            if (urlConnection != null)   
                urlConnection.disconnect();   
        }   
    }   
    
    /** 
     * 发送HttpPost请求 
     *  
     * @param strURL 
     *            服务地址 
     * @param params 
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/> 
     * @return 成功:返回json字符串<br/> 
     */  
    public static String post(String strURL, String params) {  
        System.out.println("请求URL：" + strURL);
        System.out.println("请求参数:" + params);
        try {  
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url  
                    .openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方式  
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式  
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            OutputStreamWriter out = new OutputStreamWriter(  
                    connection.getOutputStream(), "UTF-8"); // utf-8编码  
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取响应  
            int length = (int) connection.getContentLength();// 获取长度  
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8编码  
//                System.out.println(result);  
                return result;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return "error"; // 自定义错误信息  
    }
    
    /**  
     * 默认的响应字符集  
     */  
    public String getDefaultContentEncoding() {   
        return this.defaultContentEncoding;
    }   
    
    /**  
     * 设置默认的响应字符集  
     */  
    public void setDefaultContentEncoding(String defaultContentEncoding) {   
        this.defaultContentEncoding = defaultContentEncoding;   
    }   
}
 
