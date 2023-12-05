package com.restwithspring.controller.v1;

import com.restwithspring.model.vo.security.AccountCredentialsVO;
import com.restwithspring.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PostMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username")String username,
                                 @RequestHeader("Authorization")String refreshToken) {
        if (refreshToken == null || refreshToken.isBlank()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client");
        }
        var token = authService.refreshToken(username, refreshToken);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client");
        }
        return token;
    }

    @Operation(summary = "Authenticates a user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsVO vo) {
        if (vo == null || vo.getUserName() == null ||vo.getUserName().isBlank() || vo.getPassword() == null ||
                vo.getPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client");
        }
        var token = authService.signin(vo);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client");
        }
        return token;
    }

}
