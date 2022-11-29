package com.social_example_back.api.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.social_example_back.api.user.dto.UserDTO;
import com.social_example_back.api.user.entity.MyUser;
import com.social_example_back.api.user.service.UserService;
import com.social_example_back.common.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final UserService userService;
	@GetMapping("/me")
    public ApiResponse<UserDTO> getUser(MyUser myUser) {
        log.info("유저 정보 요청");
		UserDTO user = userService.getUser(myUser.getEmail());
        return ApiResponse.success("user", user);
    }
}
