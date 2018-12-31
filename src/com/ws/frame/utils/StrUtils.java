package com.ws.frame.utils;

import java.io.IOException;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;

/**
 * 字符串工具类，主要对字符串做一些简单的操作或验证
 * @author YINGFU
 * @version 1.0bate
 */
public final class StrUtils {

	/**
	 * 验证一个字符串是否是空指针或者元素为空
	 * 
	 * @param string
	 * @return ture or false
	 */
	public static boolean isNullOrEmpty(String string) {
		return null == string || "".equals(string) || string.length() == 0
				|| "null".equals(string);
	}
	
	/**
	 * 判断object类型的数据是否是空
	 * @param object 参数
	 * @return		 返回验证结果
	 */
	public static boolean isNullOrEmpty(Object object){
		return null == object || "".equals(object);
	}

	/**
	 * 访问的页面不存在
	 * 
	 * @return
	 * @throws IOException
	 */
	public static void pageNotFound(HttpServletResponse response)
			throws IOException {
		Writer out = response.getWriter();
		out.write("<center><h1>404 Not Found !</h1><center>");
		out.flush();
		out.close();
	}

	/**
	 * 对某个字符串进行md5加密
	 * 
	 * @param str
	 *            需要加密的字符串
	 * @return str 加密过的字符串
	 */
	public static final String MD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e);
		}
		return null;
	}

}
