package vn.demo.starter.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.demo.starter.security.SecurityUtils;
import vn.demo.starter.service.UserService;
import vn.demo.starter.service.dto.UserDto;
import vn.demo.starter.service.dto.request.ChangePasswordRequestDto;
import vn.demo.starter.service.dto.request.LoginRequestDto;
import vn.demo.starter.service.dto.request.RegisterRequestDto;
import vn.demo.starter.service.dto.response.LoginResponseDto;
import vn.demo.starter.service.AuthService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Authentication Resource")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> loginPortal(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Void> logout() {
        authService.logout();
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> registerPortal(@Valid @RequestBody RegisterRequestDto request) {
        authService.register(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<Void> changePasswordPortal(@Valid @RequestBody ChangePasswordRequestDto request){
        authService.changePassword(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/user")
    public ResponseEntity<UserDto> getUserInfo() {
        return ResponseEntity.ok(userService.getUserDto(SecurityUtils.getCurrentUserId()));
    }
}
