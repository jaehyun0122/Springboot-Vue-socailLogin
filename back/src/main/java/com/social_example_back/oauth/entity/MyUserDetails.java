package com.social_example_back.oauth.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.social_example_back.api.user.entity.MyUser;

import java.util.Map;

@Getter
@Setter
public class MyUserDetails extends User implements OAuth2User, OidcUser {
    private MyUser myUser;
    private Map<String, Object> attributes;

    public MyUserDetails(MyUser myUser)
    {
    	 super(myUser.getEmail(), myUser.getPassword(), AuthorityUtils.createAuthorityList(myUser.getRoleType().getCode()));
    	 this.myUser = myUser;
    }
    
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return myUser.getEmail();
    }

    @Override
    public Map<String, Object> getClaims() {
        return null;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }
    
    public static MyUserDetails create(MyUser myUser, Map<String, Object> attributes) {
    	MyUserDetails myUserDetails = new MyUserDetails(myUser);
    	myUserDetails.setAttributes(attributes);

        return myUserDetails;
    }
}
