package com.auth.demo.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {

	private String name;
	private String password;

	private String email;
	private int age;
	private String DOB;
	private String language;
	private String gender;
	private String prefix;
	private String Bio;
	private MultipartFile image;

}
