package com.letsCode.codingPlatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.letsCode.codingPlatform.model.LeaderBoard;
import com.letsCode.codingPlatform.model.Users;
import com.letsCode.codingPlatform.repository.LeaderBoardRepo;
import com.letsCode.codingPlatform.repository.UsersRepo;
import com.letsCode.codingPlatform.model.UserPrincipal;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    LeaderBoardRepo leaderBoardRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public void createUser(Users users) {
        usersRepo.findById(users.getUserName()).ifPresent(user -> {
            throw new IllegalStateException("User already exists");
        });
        users.setPassword(encoder.encode(users.getPassword()));
        usersRepo.save(users);
        LeaderBoard leaderBoard = new LeaderBoard(users.getUserName(), 0, 0, 0, 0, 0);
        leaderBoardRepo.save(leaderBoard);
    }

    public void updateUser(Users users) {
        usersRepo.save(users);
    }
    
    public void deleteUserByUseName(String userName) {
        usersRepo.deleteById(userName);
    }

    public Users getUserByUserName(String userName) {
        return usersRepo.findById(userName).orElse(null);
    }

    public List<Users> getAllUsers() {
        return usersRepo.findAll();
    }

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    Users user= usersRepo.findById(username).orElse(null);
	
	    if (user==null) {
		    System.out.println("User 404");
		    throw new UsernameNotFoundException("User 404");
	    }
		return new UserPrincipal(user);
	}
}
