package com.puzzlix.solid_task.domain.user;

import com.puzzlix.solid_task._global.config.jwt.JwtTokenProvider;
import com.puzzlix.solid_task._global.dto.CommonResponseDto;
import com.puzzlix.solid_task.domain.user.dto.UserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto<?>> signUp(@Valid @RequestBody UserRequest.SignUp request) {

        userService.signUp(request);
        return ResponseEntity.ok(CommonResponseDto.success(null, "회원가입이 완료 되었습니다"));
    }

    @PostMapping("/login/{type}")
    public ResponseEntity<CommonResponseDto<?>> login(
            @PathVariable String type,
            @Valid @RequestBody UserRequest.Login request) {
        User user = userService.login(type, request);
        // 사용자 이메을을 기반으로 JWT 토큰 생성
        String token = jwtTokenProvider.createToken(user);
        return ResponseEntity.ok(CommonResponseDto.success(token, "로그인에 성공했습니다"));
    }

    @GetMapping("/kakao-login")
    public String kakaoLogin(Model model) {
        model.addAttribute("location", UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", "3840591c69c0814fdb56fafa548e995e")
                .queryParam("redirect_uri", "http://localhost:8080/api/issues")
                .build()
                .toUriString());
        return "kakao-login";
    }


}
