package com.xinyi.xinfo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import com.xinyi.xinfo.bean.PageParams;

/**
* 〈调用RestFul接口http请求工具类〉
* @author [tao.li@xinyi-tech.com]
* @date [2017-12-14
* @see [相关类/方法][产品]
* @since V3.7.0
*/
public class HttpClientUtil {
    
    private static final org.slf4j.Logger logger =  LoggerFactory.getLogger(HttpClientUtil.class);
    
    static String NOAUTH = "";//无需鉴权
    
    public static final int CONNECTTIMEOUT = 1000*10;//超时时间
      
    public static final int CONNECTTIMEOUT_LONG = 1000*60*4;//长超时时间
    
    public static final String ASSIGN_MSRV_URL = "http://68.68.14.34/assignmsrv/";
    
    public static final String RESTFULPW = "96e79218965eb72c92a549dd5a330112";
    
    public static final String RESTFUL_USERNAME = "restful.xinyi";
    
    public static final String RESTFUL_PASSWORD = "xinyi@2017";
        
    /**
     * 获取登入验证授权token
     * @param url
     * @param pageParams
     * @return
     */
    public static String connectClientByAuth(String url, PageParams pageParams) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.putAll(pageParams.getQueryParams());
        paramMap.put("pageIndex", pageParams.getPageIndex() + "");
        paramMap.put("pageSize", pageParams.getPageSize() + "");
        paramMap.put("sortField", pageParams.getSortField());
        paramMap.put("sortOrder", pageParams.getSortOrder());
        return connectClientByAuth(url, paramMap, null);
    }
    
    public static String connectClientByAuth(String url, Map<String, Object> paramMap){
        return connectClientByAuth(url, paramMap, null);
    }
    
    /**
     * 获取登入验证授权token
     * 不同项目需要获取对于项目的token，需要分开存储
     * @param url
     * @param paramMap
     * @return
     */
    public static String connectClientByAuth(String url, Map<String, Object> paramMap,
            HttpServletRequest request) {
        Map<String,String> tokenMap = null;
        boolean needAuth = false;
        boolean isSaveRedis = true;
        String projectCode = "";//项目编码
        if (paramMap == null) {
            paramMap = new HashMap<String, Object>();
        }
        if (url.indexOf(ASSIGN_MSRV_URL) > -1) {
            projectCode = "ASSIGN_MSRV";
        }              
        //tomcat中接口密码验证
        paramMap.put("restfulCheck", RESTFULPW);
        /*RedisManager redisManager = RedisMananagerFactory.getRedisManager();
        // 查询redis存储的凭证,防止重复调用鉴权接口
        String cacheKey = JWTSECRET_REDIS + RESTFUL_USERNAME;
        Object tokenRedis = redisManager.getObject(cacheKey);
        if (tokenRedis != null) {
            tokenMap = (Map<String,String>) tokenRedis;
            if(tokenMap.get(projectCode) == null || "".equals(tokenMap.get(projectCode))){
                needAuth = true;
            }
        }else{*/
            tokenMap = new HashMap<String,String>();
            needAuth = true;
        //}
        // 请求情指一体化微服务接口
        if (needAuth == true) {
            if(projectCode == "ASSIGN_MSRV"){
                paramMap.put("userName", RESTFUL_USERNAME);
                paramMap.put("passWord", RESTFUL_PASSWORD);
                paramMap.put("onlyToken", 1);
                // 获取授权token
                String token = connectClient(ASSIGN_MSRV_URL + "/login", NOAUTH, paramMap, request);
                tokenMap.put(projectCode, token);
                if(token == null || token.indexOf("Bearer") == -1){
                    isSaveRedis = false;
                }
                // 登入失败
                if (token != null && token.indexOf("status") > -1) {
                    return token;
                }
            }
            /*if(isSaveRedis){
                redisManager.setObject(cacheKey, tokenMap, 60*30);
            }*/
        }
        return connectClient(url, tokenMap.get(projectCode), paramMap, request);
    }
    

    public static String connectClient(String url, String token , Map<String, Object> paramMap,
            HttpServletRequest request){
        return connectClient(url,token,paramMap,request,CONNECTTIMEOUT);
    }
	/**
	 * http请求服务，使用post请求资源
	 * @param url 请求地址
	 * @param token 验证token
	 * @param paramMap 参数集合
	 * @return
	 * @throws Exception
	 */
	public static String connectClient(String url, String token , Map<String, Object> paramMap,
	        HttpServletRequest request,int timeout){
		String response = "";
		HttpClient client = HttpClients.createDefault();
		final HttpPost postMethod = new HttpPost(url); 
		//请求和传输超时时间
		RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout)
		        .setConnectTimeout(timeout).build();
		postMethod.setConfig(config);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		//遍历request参数值，构造formparams
		if(request != null){
    		Map<String, String[]> requestMap = request.getParameterMap();
    		Iterator<Entry<String, String[]>> keys = requestMap.entrySet().iterator();
    	    while (keys.hasNext())
    	        {
    	            Map.Entry<String, String[]> entry = keys.next();
    	            String key = entry.getKey();
    	            String[] valueArr = entry.getValue();
    	            String value = null;
    	            if (valueArr != null && valueArr.length > 0 && !"".equals(valueArr[0]))
    	            {
    	                value = valueArr[0];
    	                if (!StringUtils.isEmpty(value))
    	                {
    	                    formparams.add(new BasicNameValuePair(key, value));
    	                }
    	            }
	        }
		}
		if (paramMap != null && !paramMap.isEmpty() ) {
			Iterator<Entry<String, Object>> paramMapKeys = paramMap.entrySet().iterator();
			while (paramMapKeys.hasNext()) {
				Map.Entry<String, Object> entry = paramMapKeys.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				if (value != null) {
					formparams.add(new BasicNameValuePair(key, value.toString()));
				}
			}
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,Consts.UTF_8);
		postMethod.setEntity(entity);
		if(!StringUtils.isEmpty(token)){
		    postMethod.addHeader("Authorization",token);
		}
		try {
			HttpResponse httpRes = client.execute(postMethod);
			response = EntityUtils.toString(httpRes.getEntity());
			//http状态码
			int status = httpRes.getStatusLine().getStatusCode();
		} catch (final IOException e) {
		    response = "{'status':'500'}";
		    logger.error("HttpClientUtil接口异常;请求地址:"+url+";抛出"+e.toString());
			//e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return response;
	}
	
	/**
	 * 使用get方式连接资源
	 * @param url
	 * @param token
	 * @param paramMap
	 * @param request
	 * @return
	 */
	public static String connectClientByGet(String url, String token , Map<String, Object> paramMap,
            HttpServletRequest request){
        String response = "";
        HttpClient client = HttpClients.createDefault();
        final HttpGet getMethod = new HttpGet(url);
        //请求和传输超时时间
        RequestConfig config = RequestConfig.custom().setSocketTimeout(CONNECTTIMEOUT)
                .setConnectTimeout(CONNECTTIMEOUT).build();
        getMethod.setConfig(config);
        if(!StringUtils.isEmpty(token)){
            getMethod.addHeader("Authorization",token);
        }
        try {
            HttpResponse httpRes = client.execute(getMethod);
            response = EntityUtils.toString(httpRes.getEntity(), "UTF-8");
            //http状态码
            int status = httpRes.getStatusLine().getStatusCode();
        } catch (final IOException e) {
            response = "{'status':'500'}";
            logger.error("HttpClientUtil接口异常;请求地址:"+url+";抛出"+e.getMessage());
            e.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }
        return response;
    }

}
