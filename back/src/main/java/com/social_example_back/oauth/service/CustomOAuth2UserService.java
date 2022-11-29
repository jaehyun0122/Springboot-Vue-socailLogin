package com.social_example_back.oauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.social_example_back.api.user.entity.MyUser;
import com.social_example_back.api.user.repository.UserRepository;
import com.social_example_back.oauth.entity.MyUserDetails;
import com.social_example_back.oauth.entity.ProviderType;
import com.social_example_back.oauth.entity.RoleType;
import com.social_example_back.oauth.exception.OAuthProviderMissMatchException;
import com.social_example_back.oauth.info.OAuth2UserInfo;
import com.social_example_back.oauth.info.OAuth2UserInfoFactory;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        try {
            return this.process(userRequest, user);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());
        MyUser savedUser = userRepository.findByEmail(userInfo.getEmail());

        if (savedUser != null) {
            if (providerType != savedUser.getProviderType()) {
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with " + providerType +
                        " account. Please use your " + savedUser.getProviderType() + " account to login."
                );
            }
            log.info("기존 유저");
            updateUser(savedUser, userInfo); // 만약 카카오 프로필 변경하면 내 db에도 update하려면 db update 처리 해줘야함.
        } else {
            log.info("신규 유저 생성");
            savedUser = createUser(userInfo, providerType);
        }

        return MyUserDetails.create(savedUser, user.getAttributes());
    }

    private MyUser createUser(OAuth2UserInfo userInfo, ProviderType providerType) {

    	LocalDateTime now = LocalDateTime.now();

    	// userInfo.getId() 아이디는 쓰임세에 따라 추가하길..

        MyUser user = new MyUser(
                userInfo.getEmail(),
                userInfo.getName(),
                userInfo.getImageUrl(),
                providerType,
                RoleType.USER,
                "Y",
                now,
                now
        );

        return userRepository.saveAndFlush(user);
    }

    private MyUser updateUser(MyUser user, OAuth2UserInfo userInfo) {
        if (userInfo.getName() != null && !user.getUsername().equals(userInfo.getName())) {
            user.setUsername(userInfo.getName());
        }

        if (userInfo.getImageUrl() != null && !user.getProfileImageUrl().equals(userInfo.getImageUrl())) {
            user.setProfileImageUrl(userInfo.getImageUrl());
        }

        return user;
    }
}
