package com.auth.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.auth.demo.entity.Profile;
import com.auth.demo.request.ProfileRequest;
import com.auth.demo.service.ProfileService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class cvController {
	@Autowired
	ProfileService profileService;

	@PostMapping("/createProfile")
	public ModelAndView createProfileControllerMethod(@ModelAttribute ProfileRequest request,
			@RequestParam("files") MultipartFile[] files, ModelAndView modelAndView) {
		log.info("method create profile called..");
		Profile response = profileService.saveProfile(request, files);

		modelAndView.addObject("profile", response);
		modelAndView.setViewName("/displayProfile");
		return modelAndView;

	}

	@GetMapping("/createPage")
	public ModelAndView createProfilePage(ModelAndView modelAndView) {
		modelAndView.setViewName("profile");
		return modelAndView;

	}

	@GetMapping("/page/updateProfile/")
	public ModelAndView updateProfilePage(ModelAndView modelAndView, @RequestParam("id") String userId) {
		System.out.println("user id is :" + userId);
		int Id = Integer.parseInt(userId);
		Profile profile = profileService.getProfile(Id);
		modelAndView.addObject("profile", profile);
		modelAndView.setViewName("updateProfile");
		return modelAndView;

	}

	@PostMapping("/updateProfile")
	public ModelAndView updateProfileMethod(@ModelAttribute ProfileRequest request, ModelAndView modelAndView) {
		log.info("method update profile called..");
		Profile response = profileService.updateProfile(request);
       
		modelAndView.addObject("profile", response);
		modelAndView.setViewName("/displayProfile");
		return modelAndView;

	}
	
	@GetMapping("/")
	public ModelAndView allUsersList(ModelAndView modelAndView) {
		List<Profile> usersList = new ArrayList<>();
		usersList= profileService.getAllUsers();
		modelAndView.addObject("userList",usersList);
		modelAndView.setViewName("usersList");
		return modelAndView;

	}
	
	@GetMapping("/deleteUser")
	public ModelAndView DeleteUserProfile(ModelAndView modelAndView,@RequestParam("id") String userId) {
		int Id = Integer.parseInt(userId);
		profileService.DeleteProfile(Id);
		return this.allUsersList(modelAndView);

	}


}
