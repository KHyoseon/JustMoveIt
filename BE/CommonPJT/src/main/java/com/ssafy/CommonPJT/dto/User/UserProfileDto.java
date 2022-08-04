package com.ssafy.CommonPJT.dto.User;

import com.ssafy.CommonPJT.domain.Authority;
import com.ssafy.CommonPJT.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {

    @Schema(description = "권한")
    private Authority authority;

    @Schema(description = "휴대폰 번호")
    private String phoneNumber;

    @Schema(description = "아이디")
    private String username;

    @Schema(description = "프로필 이미지")
    private String profileImg;


    public UserProfileDto(User user) {
        this.authority = user.getAuthority();
        this.phoneNumber = user.getPhoneNumber();
        this.username = user.getUsername();
        this.profileImg = user.getProfileImg();
    }
}
