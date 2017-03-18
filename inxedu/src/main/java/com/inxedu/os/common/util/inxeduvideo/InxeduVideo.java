package com.inxedu.os.common.util.inxeduvideo;

import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class InxeduVideo {
	  
    private static final String MAC_NAME = "HmacSHA1";    
    private static final String ENCODING = "UTF-8";    
      
    /* 
     * չʾ��һ������ָ���㷨��Կ�Ĺ��� ��ʼ��HMAC��Կ  
     * @return  
     * @throws Exception 
     *  
      public static String initMacKey() throws Exception { 
      //�õ�һ�� ָ���㷨��Կ����Կ������ 
      KeyGenerator KeyGenerator keyGenerator =KeyGenerator.getInstance(MAC_NAME);  
      //����һ����Կ 
      SecretKey secretKey =keyGenerator.generateKey(); 
      return null; 
      } 
     */  
      
    /**  
     * ʹ�� HMAC-SHA1 ǩ�������Զ�encryptText����ǩ��  
     * @param encryptText ��ǩ�����ַ���  
     * @param encryptKey  ��Կ  
     * @return  
     * @throws Exception  
     */    
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception     
    {           
        byte[] data=encryptKey.getBytes();  
        //���ݸ������ֽ����鹹��һ����Կ,�ڶ�����ָ��һ����Կ�㷨������  
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);   
        //����һ��ָ�� Mac �㷨 �� Mac ����  
        Mac mac = Mac.getInstance(MAC_NAME);   
        //�ø�����Կ��ʼ�� Mac ����  
        mac.init(secretKey);    
          
        byte[] text = encryptText.getBytes();    
        //��� Mac ����   
        return mac.doFinal(text);    
    }

    public static String createPlayToken(String fid,String SecretKey,String AccessKey) {
        if (fid == null || fid.length() == 0) {
            return "";
        }
        long deadline = System.currentTimeMillis()/1000 + 3600;
        String token = "id=" + fid + "&deadline=" + deadline + "&enablehtml5=1";
        String encoded = new BASE64Encoder().encode(token.getBytes());
        encoded = encoded.replaceAll("\r|\n", "");
        String encodeSign ="";
        byte[] sign = null;
        try {
            sign = HmacSHA1Encrypt(encoded, SecretKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        encodeSign = new BASE64Encoder().encode(sign);
        token = AccessKey + ":" + encodeSign + ":" + encoded;
        try {
            token = URLEncoder.encode(token, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("UTF-8 not supported. " + e);
            e.printStackTrace();
        }
        return token;
    }
}
