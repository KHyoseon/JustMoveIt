package com.ssafy.CommonPJT.dto.User;

import com.ssafy.CommonPJT.domain.Authority;
import com.ssafy.CommonPJT.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveDto {

    @Schema(description = "권한")
    private Authority authority;

    @Schema(description = "휴대폰 번호")
    private String phoneNumber;

    @Schema(description = "아이디")
    private String username;

    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "프로필 이미지")
    private String profileImg;

    @Builder
    public User toEntity() {
        return User.builder()
                .authority(this.authority)
                .phoneNumber(this.phoneNumber)
                .username(this.username)
                .password(this.password)
                .profileImg(this.profileImg)
                .build();
    }
}
