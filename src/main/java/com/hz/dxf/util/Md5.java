package com.hz.dxf.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

    public static void main(String[] args) {
    	String encStr=MD5(MD5("admin@DEGRPK",32)[1]+"DEGRPK",32)[1];
        System.out.println(encStr);
    }

    public static String[] MD5(String sourceStr, int ix) {
    	String[] result = {"F", null};
    	try {
            if (ix == 16 || ix == 32){
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(sourceStr.getBytes());
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
                if (ix == 16){ result = new String[] {"T", buf.toString().substring(8, 24)}; }
                if (ix == 32){ result = new String[] {"T", buf.toString()}; }
            }else{
            	result = new String[] {"F", ""}; 
            }
    	} catch (NoSuchAlgorithmException e) {
    		result = new String[] {"F", e.toString()}; 
        }
		return result;
    }
}
