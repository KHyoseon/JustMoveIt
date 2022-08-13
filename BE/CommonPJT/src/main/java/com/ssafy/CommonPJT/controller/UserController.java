package com.ssafy.CommonPJT.controller;

import com.ssafy.CommonPJT.dto.User.UserLoginDto;
import com.ssafy.CommonPJT.dto.User.UserProfileDto;
import com.ssafy.CommonPJT.dto.User.UserSaveDto;
import com.ssafy.CommonPJT.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api("UserController")
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원 가입", notes = "회원정보를 저장한다.")
    @PostMapping
    public void signUp(@RequestBody UserSaveDto requestDto) {
        userService.signUp(requestDto);
        log.info("회원가입을 진행합니다.");
    }

    @ApiOperation(value = "회원 로그인", notes = "회원 로그인")
    @PostMapping("/login")
    public ResponseEntity<UserProfileDto> signIn(@RequestBody UserLoginDto requestDto) {
        log.info("로그인을 진행합니다.");
        return new ResponseEntity<>(userService.signIn(requestDto), HttpStatus.OK);
    }

    @ApiOperation(value = "회원 조회", notes = "특정 회원을 조회한다.")
    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDto> getUser(@PathVariable Long userId) {
        log.info("회원을 조회합니다.");
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "전체 회원 조회", notes = "전체 회원을 조회한다.")
    @GetMapping
    public ResponseEntity<List<UserProfileDto>> getUserList() {
        log.info("회원 리스트를 조회합니다");
        return new ResponseEntity<>(userService.getUserList(), HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보 삭제", notes = "회원 정보를 삭제합니다.")
    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteById(userId);
    }
}
