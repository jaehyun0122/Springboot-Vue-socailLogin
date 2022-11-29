package com.social_example_back.api.user.service;

import com.social_example_back.api.user.dto.JoinDTO;
import com.social_example_back.api.user.dto.UserDTO;

public interface UserService
{
	public UserDTO getUser(String userEmail);
	public void joinUser(JoinDTO joinDTO);
}
