package com.ssafy.CommonPJT.dto.User;

import com.ssafy.CommonPJT.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {

    @Schema(description = "아이디")
    private String username;

    @Schema(description = "비밀번호")
    private String password;


    public UserLoginDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
