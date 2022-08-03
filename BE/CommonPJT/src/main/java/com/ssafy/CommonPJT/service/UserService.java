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

    public Long signIn(UserLoginDto requestDto) {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            if (user.getUsername().equals(requestDto.getUsername())) {
                if (user.getPassword().equals(requestDto.getPassword())) {
                    return user.getId();
                }
            }
        }
        return null;
    }

    public UserProfileDto getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));
        UserProfileDto userProfileDto = new UserProfileDto(user);
        return userProfileDto;
    }

    public List<UserProfileDto> getUserList() {
        List<User> userList = userRepository.findAll();
        List<UserProfileDto> userProfileDtoList = userList.stream().map(UserProfileDto::new).collect(Collectors.toList());
        return userProfileDtoList;
    }
}
