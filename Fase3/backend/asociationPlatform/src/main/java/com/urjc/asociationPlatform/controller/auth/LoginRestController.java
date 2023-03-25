package com.urjc.asociationPlatform.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urjc.asociationPlatform.security.jwt.AuthResponse;
import com.urjc.asociationPlatform.security.jwt.LoginRequest;
import com.urjc.asociationPlatform.security.jwt.UserLoginService;
import com.urjc.asociationPlatform.security.jwt.AuthResponse.Status;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/auth")
public class LoginRestController {
    @Autowired
	private UserLoginService userService;

	@Operation(summary = "Login as user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "user logged in sucessfully", content = @Content),
        @ApiResponse(responseCode = "405", description = "wrong username or password", content = @Content)
            
    })
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@CookieValue(name = "accessToken", required = false) String accessToken,
			@CookieValue(name = "refreshToken", required = false) String refreshToken,
			@RequestBody LoginRequest loginRequest) {
		
		return userService.login(loginRequest, accessToken, refreshToken);
	}

	@Operation(summary = "Logout as user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "user logged out sucessfully", content = @Content),
        @ApiResponse(responseCode = "405", description = "logout error", content = @Content)
            
    })
	@PostMapping("/logout")
	public ResponseEntity<AuthResponse> logOut(HttpServletRequest request, HttpServletResponse response) {

		return ResponseEntity.ok(new AuthResponse(Status.SUCCESS, userService.logout(request, response)));
	}
}
