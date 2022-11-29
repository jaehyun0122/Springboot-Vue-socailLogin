package com.social_example_back.api.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.social_example_back.api.user.dto.JoinDTO;
import com.social_example_back.api.user.dto.UserDTO;
import com.social_example_back.oauth.entity.ProviderType;
import com.social_example_back.oauth.entity.RoleType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
	@JsonIgnore
    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq;

    @Column(name = "EMAIL", length = 512, unique = true)
    @NotNull
    @Size(max = 512)
    private String email;

    @JsonIgnore
    @Column(name = "PASSWORD", length = 128)
    @NotNull
    @Size(max = 128)
    private String password;

    @Column(name = "USERNAME", length = 100)
    @NotNull
    @Size(max = 100)
    private String username;

    @Column(name = "PROFILE_IMAGE_URL", length = 512)
    @NotNull
    @Size(max = 512)
    private String profileImageUrl;

    @Column(name = "PROVIDER_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProviderType providerType;

    @Column(name = "ROLE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleType roleType;
    
    @Column(name = "EMAIL_VERIFIED_YN", length = 1)
    @NotNull
    @Size(min = 1, max = 1)
    private String emailVerifiedYn;

    @Column(name = "CREATED_AT")
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "MODIFIED_AT")
    @NotNull
    private LocalDateTime modifiedAt;

    public MyUser(
            @NotNull @Size(max = 512) String email,
            @NotNull @Size(max = 100) String username,
            @NotNull @Size(max = 512) String profileImageUrl,
            @NotNull ProviderType providerType,
            @NotNull RoleType roleType,
            @NotNull @Size(max = 1) String emailVerifiedYn,
            @NotNull LocalDateTime createdAt,
            @NotNull LocalDateTime modifiedAt
    ) {
        this.email = email != null ? email : "NO_EMAIL";
        this.password = "NO_PASS";
        this.username = username;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl : "";
        this.providerType = providerType;
        this.roleType = roleType;
        this.emailVerifiedYn = emailVerifiedYn;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    
	public UserDTO toDTO() {
		return new UserDTO(email,username,profileImageUrl,providerType,roleType.getCode());
	}

	public MyUser(JoinDTO joinDTO,LocalDateTime createdAt,LocalDateTime modifiedAt, String imageUrl) {
        this.email = joinDTO.getEmail();
        this.password = joinDTO.getPassword();
        this.username = joinDTO.getName();
        this.profileImageUrl = imageUrl;
        this.providerType = ProviderType.LOCAL;
        this.roleType = RoleType.USER;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
	}
}
