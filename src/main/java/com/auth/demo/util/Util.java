package com.auth.demo;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class Util {
	  public static String encodeImage(byte[] imageBytes) {
	        return Base64.encodeBase64String(imageBytes);
	    }
}
