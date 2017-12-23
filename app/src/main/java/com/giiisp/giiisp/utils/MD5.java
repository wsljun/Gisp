package com.giiisp.giiisp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5 {

	public static String getMD5(String val) {
		if (val != null) {
			return getMD5(val.getBytes());
		}
		return "";
	}

	public static String getMD5(byte[] val) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md5.digest(val);
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int temp = ((int) md5Bytes[i]) & 0xff;
				if (temp < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(temp));
			}
			return hexValue.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}



	public static String getFileMd5(String filePath){
		String value = "";
		FileInputStream in = null;
		try {
			File file = new File(filePath);
			if(!file.exists()){
				return value;
			}
			in = new FileInputStream(file);
			byte[] buffer = new byte[8192];
	        MessageDigest md5 = MessageDigest.getInstance("MD5");
	        int len;
	        while((len = in.read(buffer)) != -1){
	            md5.update(buffer, 0, len);
	        }

			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
}
