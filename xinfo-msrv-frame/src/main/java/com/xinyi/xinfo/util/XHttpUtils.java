package com.xinyi.xinfo.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class XHttpUtils
{
    public static boolean judgeURLValid(String url)
    {
        boolean isValid = false;
        CloseableHttpResponse response = null;
        try
        {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpHead httpHead = new HttpHead(url);
            response = httpclient.execute(httpHead);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200)
            {
                isValid = true;
            }
        }
        catch (Exception e)
        {
            isValid = false;
        }
        finally
        {
            try
            {
                if (response != null)
                {
                    response.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return isValid;
    }

    /**
     * 下载文件
     *
     * @param url
     * @param filePath
     */
    public static String getHttpContent(String url)
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String fileContent = null;
        
        try
        {
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse httpResponse = httpclient.execute(httpGet);
            try
            {
                HttpEntity httpEntity = httpResponse.getEntity();

                fileContent = EntityUtils.toString(httpEntity, "UTF-8");
                EntityUtils.consume(httpEntity);
            }
            finally
            {
                httpResponse.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                httpclient.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return fileContent;
    }
}
