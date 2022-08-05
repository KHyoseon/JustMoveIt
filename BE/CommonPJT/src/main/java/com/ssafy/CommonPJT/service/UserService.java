package com.ssafy.CommonPJT.service;

import com.ssafy.CommonPJT.domain.User;
import com.ssafy.CommonPJT.dto.User.UserLoginDto;
import com.ssafy.CommonPJT.dto.User.UserProfileDto;
import com.ssafy.CommonPJT.dto.User.UserSaveDto;
import com.ssafy.CommonPJT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
        User user1 = userRepository.findUserByUsername(user.getUsername());
        if (user1 == null) {
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("아이디가 중복되었습니다.");
        }
    }


    // 로그인
    public UserProfileDto signIn(UserLoginDto requestDto) {
        User user1 = userRepository.findUserByUsername(requestDto.getUsername());
        if (user1.getPassword().equals(requestDto.getPassword())) {
            return new UserProfileDto(user1);
        } else {
            return null;
        }
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

    @Transactional
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
