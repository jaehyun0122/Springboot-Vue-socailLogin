package com.social_example_back.api.user.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.social_example_back.api.user.dto.JoinDTO;
import com.social_example_back.api.user.dto.UserDTO;
import com.social_example_back.api.user.entity.MyUser;
import com.social_example_back.api.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

    public UserDTO getUser(String userEmail) {
        return userRepository.findByEmail(userEmail).toDTO();
    }

	@Override
	public void joinUser(JoinDTO joinDTO) {
		LocalDateTime now = LocalDateTime.now();
		joinDTO.setPassword(passwordEncoder.encode(joinDTO.getPassword()));

		MultipartFile multipartFile = joinDTO.getImage();
		String contentType = multipartFile.getContentType();
        String originalFileExtension = null;

        if(contentType.contains("image/jpeg")){
            originalFileExtension = ".jpg";
        }
        else if(contentType.contains("image/png")){
            originalFileExtension = ".png";
        }

        File convertFile = new File(joinDTO.getName()+""+originalFileExtension);
        try {
			if(convertFile.createNewFile()) {
			    try (FileOutputStream fos = new FileOutputStream(convertFile)) {
			        fos.write(multipartFile.getBytes());
			    }
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MyUser myUser = new MyUser(joinDTO,now,now,joinDTO.getName()+""+originalFileExtension);
		userRepository.saveAndFlush(myUser);
	}
}
