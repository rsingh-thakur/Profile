package com.auth.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
		Profile response = profileService.saveProfile(request,files);
		
		modelAndView.addObject("profile", response);
		modelAndView.setViewName("/displayProfile");
		return modelAndView;

	}

	@GetMapping("/index")
	public ModelAndView createProfilePage(ModelAndView modelAndView) {
		modelAndView.setViewName("profile");
		return modelAndView;

	}

}
