package com.restwithspring.service;

import com.restwithspring.model.vo.security.AccountCredentialsVO;
import com.restwithspring.model.vo.security.TokenVO;
import com.restwithspring.repository.UserRepository;
import com.restwithspring.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;


    public ResponseEntity signin(AccountCredentialsVO data) {
        try {
            var username = data.getUserName();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

            var user = repository.findByUserName(username);

            var tokenResponse = new TokenVO();

            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("USername" + username + " not found");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken) {
            var user = repository.findByUserName(username);

            var tokenResponse = new TokenVO();

            if (user != null) {
                tokenResponse = tokenProvider.refreshToken(refreshToken);
            } else {
                throw new UsernameNotFoundException("USername" + username + " not found");
            }
            return ResponseEntity.ok(tokenResponse);
    }
}
