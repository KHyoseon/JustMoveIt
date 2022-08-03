package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.dto.User.UserLoginDto;
import com.ssafy.CommonPJT.dto.User.UserProfileDto;
import com.ssafy.CommonPJT.dto.User.UserSaveDto;
import com.ssafy.CommonPJT.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("UserController")
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원 가입", notes = "회원정보를 저장한다.")
    @PostMapping
    public void signUp(@RequestBody UserSaveDto requestDto) {
        userService.signUp(requestDto);
    }

    @ApiOperation(value = "회원 로그인", notes = "회원 로그인")
    @PostMapping("/login")
    public Long signIn(@RequestBody UserLoginDto requestDto) {
        return userService.signIn(requestDto);
    }

    @ApiOperation(value = "회원 조회", notes = "특정 회원을 조회한다.")
    @GetMapping("/{userId}")
    public UserProfileDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @ApiOperation(value = "전체 회원 조회", notes = "전체 회원을 조회한다.")
    @GetMapping
    public List<UserProfileDto> getUserList() {
        return userService.getUserList();
    }
}
