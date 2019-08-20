package com.xinyi.xinfo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FTPHelper
{
    
    private static final Logger logger = LoggerFactory.getLogger(FTPHelper.class);
    
    private FTPClient ftpClient = null;

    // FTP服务器地址
    public String hostName = "";

    // FTP服务器默认端口
    public Integer defaultport = 21;

    // 登录名
    private String userName = "";

    // 登录密码
    private String password = "";

    // 默认访问的远程目录
    private String remoteDir = "";

    public FTPHelper()
    {
        this.ftpClient = new FTPClient();
        this.ftpClient.configure(FTPHelper.Config());
        this.ftpClient.setControlEncoding("GBK");

        // 登录
        this.login();

        // 切换目录
        this.changeDir(remoteDir);
        this.setFileType(FTPClient.BINARY_FILE_TYPE);
    }

    public FTPHelper(String remoteDir)
    {
        this.ftpClient = new FTPClient();
        this.ftpClient.configure(FTPHelper.Config());
        this.ftpClient.setControlEncoding("GBK");
        // 登录
        this.login();

        // 切换目录
        this.changeDir(remoteDir);
        this.setFileType(FTPClient.BINARY_FILE_TYPE);
    }
    
    public FTPHelper(String userName, String password, String remoteDir)
    {
        this.password = password;
        this.userName = userName;
        this.remoteDir = remoteDir;
        this.ftpClient = new FTPClient();
        this.ftpClient.configure(FTPHelper.Config());
        this.ftpClient.setControlEncoding("GBK");
        this.ftpClient.setControlEncoding("GBK");
        // 登录
        this.login();
        // 切换目录
        this.changeDir(remoteDir);
        this.setFileType(FTPClient.BINARY_FILE_TYPE);
    }

    public FTPHelper(String userName, String password)
    {
        this.password = password;
        this.userName = userName;
        this.ftpClient = new FTPClient();
        this.ftpClient.configure(FTPHelper.Config());
        this.ftpClient.setControlEncoding("GBK");
        this.ftpClient.setControlEncoding("GBK");
        // 登录
        this.login();
        this.setFileType(FTPClient.BINARY_FILE_TYPE);
    }
 
    /** */
    /**
     * @param hostName
     *            主机地址
     * @param port
     *            端口号
     * @param userName
     *            用户名
     * @param password
     *            密码
     * @param remoteDir
     *            默认工作目录
     * @param is_zhTimeZone
     *            是否是中文FTP Server端
     * @return
     */
    public FTPHelper(String hostName, int port, String userName, 
    		String password, String remoteDir, boolean is_zhTimeZone)
    {
        this.hostName = hostName;
        this.userName = userName;
        this.password = password;
        this.remoteDir = remoteDir == null ? "" : remoteDir;
        this.ftpClient = new FTPClient();
        if (is_zhTimeZone)
        {
            this.ftpClient.configure(FTPHelper.Config());
            this.ftpClient.setControlEncoding("GBK");
        }
        // 登录
        this.login();
        // 切换目录
        this.changeDir(this.remoteDir);
        this.setFileType(FTPClient.BINARY_FILE_TYPE);

        ftpClient.setDefaultPort(port);
    }
     
    public FTPClient getFtpClient() {
		return ftpClient;
	}

	public void setFtpClient(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir;
	}

	public String getHostName()
    {
        return hostName;
    }

    public String getRemoteDir()
    {
        return this.remoteDir;
    }

    /**
     * 登录FTP服务器
     */
    public void login()
    {
        try
        {
            ftpClient.connect(hostName);
            ftpClient.login(this.userName, this.password);
        }
        catch (FTPConnectionClosedException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static FTPClientConfig Config()
    {
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
        conf.setRecentDateFormatStr("MM月dd日 HH:mm");
        // conf.setRecentDateFormatStr("(YYYY年)?MM月dd日( HH:mm)?");
        return conf;
    }

    /**
     * 变更工作目录
     * 
     * @param remoteDir
     * 
     */
    public void changeDir(String remoteDir)
    {
        try
        {
            this.remoteDir = remoteDir;
            boolean flag = ftpClient.changeWorkingDirectory(remoteDir);
            logger.debug("切换目录："+remoteDir+ (flag ? "成功" : "失败"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // logger.debug("变更工作目录为:" + remoteDir);
    }

    /**
     * 返回上一级目录(父目录)
     */
    public void toParentDir()
    {
        try
        {
            ftpClient.changeToParentDirectory();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 列出当前工作目录下所有文件
     */
    public String[] listAllFiles()
    {
        String[] names = this.listFiles("*");
        return this.sort(names);
    }

    public FTPFile[] listFiles() throws IOException
    {
        FTPFile[] files = this.ftpClient.listFiles();
        return files;
    }
    
    /**
     * 列出指定工作目录下的匹配文件
     * 
     * @param dir
     *            exp: /cim/
     * @param file_regEx
     *            通配符为*
     */
    public String[] listAllFiles(String dir, String file_regEx)
    {
        String[] names = this.listFiles(dir + file_regEx);
        return this.sort(names);
    }

    /**
     * 列出匹配文件
     * 
     * @param file_regEx
     *            匹配字符,通配符为*
     */
    public String[] listFiles(String file_regEx)
    {
        try
        {

            String[] name = ftpClient.listNames(file_regEx);
            if (name == null)
                return new String[0];

            return this.sort(name);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new String[0];
    }

//    public void lists(String reg)
//    {
//        try
//        {
//            String[] a = ftpClient.listNames(reg);
//            if (a != null)
//            {
//                for (String b : a)
//                {
//                    //logger.debug(b);
//                }
//            }
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }

    /**
     * 设置传输文件的类型[文本文件或者二进制文件]
     * 
     * @param fileType
     *            --BINARY_FILE_TYPE,ASCII_FILE_TYPE
     */
    public void setFileType(int fileType)
    {
        try
        {
            ftpClient.setFileType(fileType);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     * 
     * @param localFilePath
     *            --本地文件路径+文件名
     * @param newFileName
     *            --新的文件名
     */
    public boolean uploadFile(String localFilePath, String remote)
    {
        ftpClient.enterLocalPassiveMode();
        // 上传文件
        // logger.debug(localFilePath);
        BufferedInputStream buffIn = null;
        boolean bln = false;
        try
        {
            buffIn = new BufferedInputStream(new FileInputStream(localFilePath));

            String remoteFileName = remote;
            if (remote.contains("/"))
            {
                remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);
                String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
                if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(directory))
                {
                    // 如果远程目录不存在，则递归创建远程服务器目录
                    int start = 0;
                    int end = 0;
                    if (directory.startsWith("/"))
                    {
                        start = 1;
                    }
                    else
                    {
                        start = 0;
                    }
                    end = directory.indexOf("/", start);
                    while (true)
                    {
                        String subDirectory = remote.substring(start, end);
                        if (!ftpClient.changeWorkingDirectory(subDirectory))
                        {
                            if (ftpClient.makeDirectory(subDirectory))
                            {
                                ftpClient.changeWorkingDirectory(subDirectory);
                            }
                            else
                            {
                                if (buffIn != null)
                                {
                                    buffIn.close();
                                }
                                return false;
                            }
                        }

                        start = end + 1;
                        end = directory.indexOf("/", start);

                        // 检查所有目录是否创建完毕
                        if (end <= start)
                        {
                            break;
                        }
                    }
                }
            }  
            bln = ftpClient.storeFile(remoteFileName, buffIn);
            if (bln)
            {
                logger.debug("上传文件:" + remoteFileName + "成功!");
            }
            else
            {
                logger.debug("上传文件:" + remoteFileName + "失败,原文件路径:" + localFilePath);
            }
        }
        catch (Exception e)
        {
            logger.debug(e.toString());
            logger.debug("上传失败!");
        }
        finally
        {
            try
            {
                if (buffIn != null)
                    buffIn.close();
                return bln;
            }
            catch (Exception e)
            {
                logger.debug("流关闭失败!");
            }
        }
        return bln;
    }
    
    
    /**
     * 上传文件
     * 
     * @param localFilePath
     *            --本地文件路径+文件名
     * @param newFileName
     *            --新的文件名
     */
    public boolean uploadFile(InputStream is, String remote, String filename)
    {
        ftpClient.enterLocalPassiveMode();
        // 上传文件
        // logger.debug(localFilePath);
        BufferedInputStream buffIn = null;
        boolean bln = false;
        try
        {
            buffIn = new BufferedInputStream(is);

            String remoteFileName = remote;
            if (remote.contains("/"))
            {
                remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);
                String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
                if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(directory))
                {
                    // 如果远程目录不存在，则递归创建远程服务器目录
                    int start = 0;
                    int end = 0;
                    if (directory.startsWith("/"))
                    {
                        start = 1;
                    }
                    else
                    {
                        start = 0;
                    }
                    end = directory.indexOf("/", start);
                    while (true)
                    {
                        String subDirectory = remote.substring(start, end);
                        if (!ftpClient.changeWorkingDirectory(subDirectory))
                        {
                            if (ftpClient.makeDirectory(subDirectory))
                            {
                                ftpClient.changeWorkingDirectory(subDirectory);
                            }
                            else
                            {
                                // logger.debug("创建目录失败");
                                if (buffIn != null)
                                {
                                    buffIn.close();
                                }
                                return false;
                            }
                        }

                        start = end + 1;
                        end = directory.indexOf("/", start);

                        // 检查所有目录是否创建完毕
                        if (end <= start)
                        {
                            break;
                        }
                    }
                }
            }
            
            bln = ftpClient.storeFile(remoteFileName, buffIn);
            if (bln)
            {
                logger.debug("上传文件:" + remoteFileName + "成功!");
            }
            else
            {
                logger.debug("上传文件:" + remoteFileName + "失败,原文件路径:" + filename);
            }
        }
        catch (Exception e)
        {
            logger.debug(e.toString());
            logger.debug("上传失败!");
        }
        finally
        {
            try
            {
                if (buffIn != null)
                    buffIn.close();
                return bln;
            }
            catch (Exception e)
            {
                logger.debug("流关闭失败!");
            }
        }
        return bln;
    }

    /**
     * ftp文件上传
     * 
     * @Description
     * @param file
     *            上传文件
     * @param fileName
     *            文件名称
     * @param dir
     *            ftp目录
     * @Author qiming.liu
     * @Date Jul 25, 2013
     */
    public boolean upload(File file, String fileName, String[] dir)
    {
        boolean flag = false;
        for (int i = 0; i < dir.length; i++)
        {
            changeDir(dir[i]);
        }
        InputStream in = null;
        try
        {
            in = new FileInputStream(file);
            flag = ftpClient.storeFile(fileName, in);
            if (flag)
            {
                logger.debug("文件上传成功!");
            }
            else
            {
                logger.debug("文件上传失败!");
            }
        }
        catch (Exception e)
        {
            // logger.debug("文件上传失败!");
            e.printStackTrace();
        }
        finally
        {
            try
            {
                in.close();
                close();
            }
            catch (Exception e)
            { 
                e.printStackTrace();
            }
        }
        return flag;
    }
     

    /**
     * 下载文件(单个)
     * 
     * @param remoteFileName  --服务器上的文件名
     * @param localFileName   --本地路径+文件名
     */
    public boolean downloadFile(String remoteFileName, String localFileName)
    {
        BufferedOutputStream buffOut = null;
        boolean success = true;
        try
        {
            buffOut = new BufferedOutputStream(new FileOutputStream(localFileName));
            success = ftpClient.retrieveFile(remoteFileName, buffOut);
            logger.debug("status:"+success);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                if (buffOut != null)
                    buffOut.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return success;
    }
    
    public void mkDirs(String path){
    	File fl = new File(path);
    	fl.mkdirs();
    }
    
    public FTPFile readFtpFile(String remoteFileName){
        
    	FTPFile ff = null;
    	try {
			FTPFile[]  arrFiles = ftpClient.listFiles();
			for(int index = 0;index < arrFiles.length;index++){
				ff = arrFiles[index];
				if(remoteFileName.equals(ff.getName())){
					return ff;
				}
			}
		} catch (IOException e) { 
			e.printStackTrace();
		}
    	return ff;
    }
    
    
    public InputStream downloadFileForLinux(String remoteFileName)
    {   
    	InputStream is = null;
        try{  
        	is = ftpClient.retrieveFileStream(remoteFileName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally{
            
        }
        return is;
    }

    public void completePendingCommand(){
        try
        {
            ftpClient.completePendingCommand();//传输完毕一定要告诉ftp
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 下载文件(单个),并返回文件长度
     * 
     * @param remoteFileName
     *            --服务器上的文件名
     * @param localFileName
     *            --本地路径+文件名
     */
    public long downFile(String remoteFileName, String localFileName)
    {
        BufferedOutputStream buffOut = null;
        long count = 0l;
        InputStream is = null;
        try
        {
            buffOut = new BufferedOutputStream(new FileOutputStream(localFileName));
            is = ftpClient.retrieveFileStream(remoteFileName);
            byte[] bytes = new byte[1024];
            int c;
            if (is.available() == 0)
            {
                count = 0l;
            }
            else
            {
                while ((c = is.read(bytes)) != -1)
                {
                    buffOut.write(bytes, 0, c);
                    count = count + c;
                }
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
                if (buffOut != null)
                    buffOut.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return count;
    }

    /**
     * 下载文件(单个)
     * 
     * @param remoteFileName
     *            --服务器上的文件名
     * @param 输出流
     *            --
     */
    public boolean downloadFile(String remoteFileName, OutputStream out)
    {
    	boolean bl = true;
        BufferedOutputStream buffOut = null;
        try
        {
            buffOut = new BufferedOutputStream(out);
            bl = ftpClient.retrieveFile(remoteFileName, buffOut);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                if (buffOut != null)
                    buffOut.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return bl;
    }

    /**
     * 关闭FTP连接
     */
    public void close()
    {
        try
        {
            if (ftpClient != null)
            {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 冒泡排序字符串(从大到小)
     */
    public String[] sort(String[] str_Array)
    {
        if (str_Array == null)
        {
            throw new NullPointerException("The str_Array can not be null!");
        }
        String tmp = "";
        for (int i = 0; i < str_Array.length; i++)
        {
            for (int j = 0; j < str_Array.length - i - 1; j++)
            {
                if (str_Array[j].compareTo(str_Array[j + 1]) < 0)
                {
                    tmp = str_Array[j];
                    str_Array[j] = str_Array[j + 1];
                    str_Array[j + 1] = tmp;
                }
            }
        }
        return str_Array;
    }

    public void deleteFile(String fileName)
    {
        if (null != fileName)
        {
            try
            {
                ftpClient.dele(fileName);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建文件夹
     * 
     * @param pathname
     */
    public void createDirectroy(String pathname)
    {
        try
        {
            ftpClient.makeDirectory(pathname);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
  /* public void listAllFile(String path) throws IOException{
        FTPFile[] files=ftpClient.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            boolean flag=false;
            if(files[i].isDirectory()){
               String name= files[i].getName();
                if(files[i].getName().contains("news")){
                   String currpath =path+name+"/";
                   ftpClient.changeWorkingDirectory(currpath);
                   flag=true;
                   listAllFile(currpath);
                }else if(flag){
                    if(files[i].isFile()){
                        System.err.println(files[i].getName());
                    }
                }
            }
        }
    }*/
}
