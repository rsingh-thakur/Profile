package com.auth.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileService {

	@Value("${image.upload.path}")
	private String imageUploadPath;
	
	@Autowired
	ProfileRepo profileRepo;

	public Profile saveProfile(ProfileRequest request, MultipartFile[] files) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date DobDate = dateFormat.parse(request.getDOB());
			Date sqlDate = new Date(DobDate.getTime());
			Profile profile = new Profile();
			profile.setAge(request.getAge());
			profile.setBio(request.getBio());
			
			profile.setImagePath(request.getImage().getOriginalFilename());
			profile.setEmail(request.getEmail());
			profile.setGender(request.getGender());
			profile.setLanguage(request.getLanguage());
			profile.setName(request.getName());
			profile.setPassword(request.getPassword());
			profile.setPrename(request.getPrefix());
			profile.setDOB(sqlDate);

			List<UserFile> userFiles = new ArrayList<>();
			for (MultipartFile file : files) {
				UserFile userFile = new UserFile();
				userFile.setContentType("text");

				System.out.println("file getting copied");

				userFile.setData(file.getBytes());
				userFile.setFileName(file.getName());
				userFiles.add(userFile);
			}
			
			String imagePath = imageUploadPath + request.getImage().getOriginalFilename();
			Path destination = Paths.get(imagePath);
			try {
				Files.copy(request.getImage().getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();

			}
			
			profile.setUserFiles(userFiles);
			return profileRepo.save(profile);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
