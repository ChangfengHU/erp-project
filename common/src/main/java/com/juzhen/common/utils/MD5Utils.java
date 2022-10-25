package com.juzhen.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.tomcat.util.buf.HexUtils;

public class MD5Utils {

	/**
	 * @Description: 对字符串进行md5加密
	 */
	public static String getMD5Str(String strValue) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
		return newstr;
	}
	public static String md5(String password) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md5.digest(password.getBytes("utf-8"));
			return HexUtils.toHexString(md5Bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//	public static void main(String[] args) {
//		try {
//			String md5 = getMD5Str("imooc");
//			System.out.println(md5);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
