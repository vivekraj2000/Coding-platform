package com.letsCode.codingPlatform.controller;

import org.springframework.web.bind.annotation.RestController;

import com.letsCode.codingPlatform.model.Users;
import com.letsCode.codingPlatform.service.JwtService;
import com.letsCode.codingPlatform.service.LeaderBoradService;
import com.letsCode.codingPlatform.service.RunCodeService;
import com.letsCode.codingPlatform.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    LeaderBoradService leaderBoradService;

    @Autowired
    RunCodeService runCodeService;

    @Autowired
	private JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

    @PostMapping("/user/register")
    public void createUser(@RequestBody Users users) {
        usersService.createUser(users);
    }

    @PostMapping("/user/login")
    public String loginUser(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

		if(authentication.isAuthenticated())
			return jwtService.generateToken(userName);
		else
			return "Login Failed";
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody Users users) {
        usersService.updateUser(users);
    }

    @DeleteMapping("/users")
    public void deleteUser(@RequestParam("userName") String userName) {
        usersService.deleteUserByUseName(userName);
        leaderBoradService.deleteUserInLeaderBoard(userName);
        runCodeService.deleteSubmissionsOfUser(userName);
    }

    @GetMapping("/users")
    public Users getUser(@RequestParam("userName") String userName) {
        return usersService.getUserByUserName(userName);
    }
}
