package com.example.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.userservice.dto.UserResponse;
import com.example.userservice.model.Department;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;



@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	//public static final String USER_SERVICE="userService";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public User create(User d) {
		// TODO Auto-generated method stub
		return userRepository.save(d);
	}
	
	//@CircuitBreaker(name =  USER_SERVICE,fallbackMethod = "getAllAvailableProducts")
	public UserResponse getOne(Long id) {
		// TODO Auto-generated method stub
		User u =  userRepository.findUserById(id);
		Department d = restTemplate.getForObject("http://department-serivce/api/department/" + u.getDepartmentId(), Department.class);
		System.out.println(d.toString());
//		
		UserResponse uResponse = new UserResponse(u.getId(), u.getFirstName(), u.getLastName(),d.getDepartmentName() ,d.getDepartmentAddress(), d.getDepartmentCode());
		return uResponse;
	}
	

}
