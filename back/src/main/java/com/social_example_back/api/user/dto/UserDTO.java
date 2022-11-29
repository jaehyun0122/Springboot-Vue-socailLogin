package com.social_example_back.api.user.dto;

import com.social_example_back.oauth.entity.ProviderType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class UserDTO {
	private String email;
    private String username;
    private String profileImageUrl;
    private ProviderType providerType;
    private String roleCode;
}
