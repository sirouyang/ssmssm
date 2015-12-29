package com.ssmssm.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.postgresql.util.Base64;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;


/**
 * 文件处理工具辅助类.
 * <p>Company:上海中信信息发展股份有限公司</p>
 */
public class FileUtilEx {
	protected static Log log = LogFactory.getLog(new FileUtilEx().getClass());
	
	public static Document getXmlDocumentByXmlPath(String xmlPath){
		InputStream is = FileUtilEx.getInputStreamByRelativePath(xmlPath);
		Document document = null;
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(is);
		} catch (DocumentException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally{
			FileUtilEx.closeInputStream(is);
		}
		
		return document;
	}

	public static InputStream getInputStream(String filePath){
		return new FileUtilEx().getClass().getResourceAsStream(filePath);
	}
	
	public static InputStream getInputStreamByRelativePath(String relativePath){
	    WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
		String filePath = servletContext.getRealPath("/") + relativePath;
		File file = new File(filePath);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return fis;
	}
	
	/**
	 * 判断文件父文件夹是否存在，如果不存在则创建路径
	 * @param file 要判断的文件
	 */
	public static void mkParentDirs(File file){
		File parent=file.getParentFile();		
		if(!parent.exists()){			
			parent.mkdirs();
		}			
	}
	
	public static void closeInputStream(InputStream is){
		IOUtils.closeQuietly(is);
	}
	
	/**
	 * 将文件路径中的分隔符替换成系统合法的.
	 */
	public static String ConvertPath(String s) {
		StringBuffer sbuf = new StringBuffer();
		int j = 0, k = 0;

		while (k < s.length()) {
			j = s.indexOf("\\", k);
			if (j < 0) {
				sbuf.append(s.substring(k, s.length()));
				k = s.length();
			} else {
				sbuf.append(s.substring(k, j));
				sbuf.append("/");
				k = j + "\\".length();
			}
		}

		return sbuf.toString();
	}
	
	public static StringBuffer readFileToString(File source) throws Exception {
		if (source != null && source.exists() && source.canRead()) {
			StringBuffer document = new StringBuffer();
			BufferedReader reader = null;
			InputStreamReader isReader = null;
			FileInputStream fiStream = null;

			try {
				fiStream = new FileInputStream(source);
				isReader = new InputStreamReader(fiStream,"ascii");
				reader = new BufferedReader(isReader);
				String tmp = null;
				while ((tmp = reader.readLine()) != null) {
					document.append(tmp);
				}
			} catch (Exception e) {
				throw e;
			} finally {
				IOUtils.closeQuietly(reader);
				IOUtils.closeQuietly(isReader);
				IOUtils.closeQuietly(fiStream);
			}
			
			return document;
		}

		return null;
	}
	
	public static void writeFile(byte[] source,File outF) throws Exception {
		if(source==null)return;
		//判断是否存在文件夹,并生成
		File parent=outF.getParentFile();		
		if(!parent.exists()){
			parent.mkdirs();
		}
		
		FileOutputStream out=null;
		try {
			out = new FileOutputStream(outF);
			out.write(source,0,source.length);
			out.flush();
		}catch (Exception e) {			
			throw e;
		}finally{
			IOUtils.closeQuietly(out);
		}		
	}
	
	/**
     * aes文件解密
     * @param sSrc
     * @param sKey
     * @param sFile
     * @throws Exception
     */
    public static boolean fileDecrypt(String sKey, String sIv, String sFile, OutputStream os) throws Exception {
    	boolean resule = true;
    	
	    try {
	        // 判断Key是否正确
	        if (sKey == null) {
	            System.out.print("Key为空null");
	            return false;
	        }
	        // 判断Key是否为16位
	        if (sKey.length() != 16) {
	            System.out.print("Key长度不是16位");
	            return false;
	        }
	        // 判断sIv是否正确
	        if (sIv == null) {
	        	System.out.print("sIv为空null");
	        	return false;
	        }
	        // 判断sIv是否为16位
	        if (sIv.length() != 16) {
	        	System.out.print("sIv长度不是16位");
	        	return false;
	        }
	        if (sFile == null || (sFile != null && "".equals(sFile.trim()))) {
		    	System.out.print("文件名不能为空");
		    	return false;
		    }
	        
	        byte[] raw = sKey.getBytes("ASCII");
	        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
	        File file = new File(ConvertPath(sFile));
	        if (file != null && file.exists() && file.canRead()) {
		        StringBuffer base64Encrypted = readFileToString(file);
		        
		        if (base64Encrypted != null) {
			        byte[] encrypted1 = Base64.decode(base64Encrypted.toString());//先用base64解密
			        //返回被加密的文件
			        
			        if (os != null) {
			        	os.write(cipher.update(encrypted1, 0, encrypted1.length));
			        	os.write(cipher.doFinal());
			        	os.flush();
			        }
		        }
	        }
	    } catch (Exception ex) {
	    	resule = false;
	    	throw ex;
	    }
	    
	    return resule;
	}
    
	/**
	 * aes文件加密
	 * @param sKey
	 * @param sourceFile
	 * @param toFile
	 * @return
	 * @throws Exception
	 */
	public static Boolean Encrypt(String sKey, String sIv, String sourceFile, String toFile) throws Exception {
	    if (sKey == null) {
	        System.out.print("Key为空null");
	        return false;
	    }
	    // 判断Key是否为16位
	    if (sKey.length() != 16) {
	        System.out.print("Key长度不是16位");
	        return false;
	    }
	    // 判断sIv是否正确
        if (sIv == null) {
        	System.out.print("sIv为空null");
        	return false;
        }
        // 判断sIv是否为16位
        if (sIv.length() != 16) {
        	System.out.print("sIv长度不是16位");
        	return false;
        }
	    if (sourceFile == null || (sourceFile != null && "".equals(sourceFile.trim()))) {
	    	System.out.print("源文件文件名不能为空");
	    	return false;
	    }
	    if (toFile == null || (toFile != null && "".equals(toFile.trim()))) {
	    	System.out.print("目标文件文件名不能为空");
	    	return false;
	    }
	    
	    byte[] raw = sKey.getBytes();
	    SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
	    IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
	    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	    //读文件
	    File file = new File(ConvertPath(sourceFile)); //创建文件实例，设置路径为方法参数  
	    if (file != null && file.exists() && file.canRead()) {
		    byte[] buffer = readFileToBytes(file);
		    
		    if (buffer != null) {
			    byte[] encrypted = cipher.doFinal(buffer);
			    
			    String base64Encrypted = Base64.encodeBytes(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
			    
			    writeFile(base64Encrypted.getBytes(), new File(ConvertPath(toFile)));
		    }
		    
		    return true;
	    }
	    
	    return false;
	}
	
	/**
	 * 将文件读到byte[]中.
	 * @throws IOException
	 * @author Reamy(杨木江 yangmujiang@sohu.com)
	 * @date 2014-01-14  10:56:36
	 */
	public static byte[] readFileToBytes(File source) throws IOException {
		if (source != null && source.exists() && source.canRead()) {
			byte[] buffer = new byte[(int)source.length()];
			FileInputStream fiStream = null;

			try {
				fiStream = new FileInputStream(source);
				fiStream.read(buffer);
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			} finally {
				IOUtils.closeQuietly(fiStream);
			}
			
			return buffer;
		}
		
		return null;
	}
	
	public static String trimLastFileSeparator(String filePath) {
		if (filePath != null && !"".equals(filePath.trim())) {
			while (filePath.trim().endsWith("/")) {
				filePath = filePath.substring(0,filePath.lastIndexOf("/"));
			}
		}
		
		return filePath;
	}
}
