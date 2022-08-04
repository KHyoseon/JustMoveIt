package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.User;
import com.ssafy.CommonPJT.dto.User.UserLoginDto;
import com.ssafy.CommonPJT.dto.User.UserProfileDto;
import com.ssafy.CommonPJT.dto.User.UserSaveDto;
import com.ssafy.CommonPJT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    @Transactional
    public void signUp(UserSaveDto requestDto) {
        User user = requestDto.toEntity();
        List<User> userList = userRepository.findAll();
        for (User user1 : userList) {
            if (user1.getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("아이디가 중복되었습니다.");
            }
        }
        userRepository.save(user);
    }


    // 로그인
    public UserProfileDto signIn(UserLoginDto requestDto) {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            if (user.getUsername().equals(requestDto.getUsername())) {
                if (user.getPassword().equals(requestDto.getPassword())) {
                    UserProfileDto user1 = new UserProfileDto(user);
                    return user1;
                }
            }
        }
        return null;
    }


    // 회원 조회
    public UserProfileDto getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
        UserProfileDto userProfileDto = new UserProfileDto(user);
        return userProfileDto;
    }


    // 회원 리스트 조회
    public List<UserProfileDto> getUserList() {
        List<User> userList = userRepository.findAll();
        List<UserProfileDto> userProfileDtoList = userList.stream().map(UserProfileDto::new).collect(Collectors.toList());
        return userProfileDtoList;
    }
}
