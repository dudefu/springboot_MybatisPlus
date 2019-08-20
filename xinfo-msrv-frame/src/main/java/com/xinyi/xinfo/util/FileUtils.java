package com.xinyi.xinfo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * <p>
 * HTTP工具类
 * </p>
 * 
 * @author hubin
 * @Date 2014-5-8
 */
public class FileUtils
{

    private static final Logger logger = Logger.getLogger("HttpUtil");
    
    /**
     * 读文件
     * @param filePath
     * @return
     */
    public static String readFile(String filePath) {
    	File file = new File(filePath);
    	return readFile(file); 
    }
    
    /**
     * 读文件
     * @param filePath
     * @return
     */
    public static String readFile(File filePath) { 
    	String lineText;
    	StringBuffer msg = new StringBuffer();;
    	InputStreamReader in = null;
    	String encoding = "GBK";
    	BufferedReader bufferReader = null;
    	try {
    		in = new InputStreamReader(new FileInputStream(filePath),encoding);
    		bufferReader = new BufferedReader(in);
    		while((lineText = bufferReader.readLine()) != null) {
    			msg.append(lineText);
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		try {
    			if(in!=null)
				in.close();
    			if(bufferReader != null)
				bufferReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
    	}
    	return msg.toString();
    }

    /**
	 * 判断文件是否存在(文件名不区分大小写)
	 * @param filePath 文件路径，例如：F:\a.txt
	 * @return 存在，返回true；否则，返回false
	 */
	public static boolean isExist(String filePath) {
		File file = null;
		boolean boo = false;
		try {
			file = new File(filePath);
			boo = file.exists();
		} catch (Exception e) {
			e.printStackTrace();
			boo = false;
		}
		return boo;
	}
	
	/**
	 * 创建文件夹(目录)
	 * @param folderPath
	 *            文件夹路径，例如：F:\\bb
	 * @return file流
	 */
	public static File createDir(String folderPath) {
		File dirFile = null;
		try {
			dirFile = new File(folderPath);
			// 当前不存在，且路径是文件夹(目录)时创建
			if (!(dirFile.exists()) && !(dirFile.isDirectory())) {
				dirFile.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dirFile;
	}

	/**
	 * 创建文件(文件夹需存在)
	 * @param filePath
	 *            文件路径，例如F:\\a.txt
	 * @return file
	 */
	public static File createFile(String filePath) {
		File file = new File(filePath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * 新建文件(带内容) -- 文件夹需存在
	 * @param filePath  文件路径及名称，如：F:\\a.txt
	 * @param fileContent  要写入的文件内容
	 * @return
	 */
	public static void createFile(String filePath, String fileContent) {
		try {
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			String strContent = fileContent;
			myFile.println(strContent);
			resultFile.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * @Title : createFileSecondPath
	 * @Description : 创建二级目录的文件
	 * @param 
	 * @return void
	 * @throws
	 */
	public static void createFileSecondPath(String filePath){
		try {
			File myFilePath = new File(filePath);
			//创建父文件夹
			if(!(myFilePath.getParentFile().exists()) && !(myFilePath.getParentFile().isDirectory())){
				myFilePath.getParentFile().mkdirs();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件或空文件夹
	 * @param filePath
	 * 文件路径，如F:\bb\b.txt
	 * @return boolean
	 */
	public static void delFile(String filePath) {
		try {
			File myDelFile = new File(filePath);
			myDelFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件夹(包含文件夹下的文件)
	 * @param folderPath
	 * 文件夹路径，如F:\bb
	 * @return
	 */
	public static void delFolder(String folderPath) {
		delFolderByALLFile(folderPath); // 删除完里面所有内容
		delFile(folderPath);
	}

	/**
	 * 删除文件夹里面的所有文件
	 * @param folderPath
	 * 文件夹路径，例如：F:\bb
	 */
	public static void delFolderByALLFile(String folderPath) {
		File file = new File(folderPath);
		if (!file.exists())
			return;
		if (!file.isDirectory())
			return;
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (folderPath.endsWith(File.separator)) {
				temp = new File(folderPath + tempList[i]);
			} else {
				temp = new File(folderPath + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delFolderByALLFile(folderPath + "/" + tempList[i]);
			}
		}
	}
}
