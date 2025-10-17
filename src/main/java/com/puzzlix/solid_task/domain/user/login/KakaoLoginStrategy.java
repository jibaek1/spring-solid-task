package com.puzzlix.solid_task.domain.user.login;

import com.puzzlix.solid_task.domain.user.User;
import com.puzzlix.solid_task.domain.user.dto.UserRequest;

public class KakaoLoginStrategy implements LoginStrategy {
    @Override
    public User login(UserRequest.Login request) {
        return null;


    }

    @Override
    public boolean supports(String type) {
        return false;
    }
}
