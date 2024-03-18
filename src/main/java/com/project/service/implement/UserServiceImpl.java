package com.project.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dto.UserDto;
import com.project.entities.User;
import com.project.entities.engineer.Aerospace;
import com.project.exceptions.ResourceNotFoundException;
import com.project.repo.UserRepository;
import com.project.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , userDto.getFullname(), userDto.getRole());
		user.setRole("USER");
		return userRepository.save(user);
	}
	
	 @Override
	    public UserDto getCurrentUser() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        if (authentication != null && authentication.isAuthenticated()) {
	            String userEmail = authentication.getName();
	            User user = userRepository.findByEmail(userEmail);
	            
	            if (user != null) {
	                return new UserDto(user.getUserId(), user.getFullname(),user.getEmail());
	            }
	        }

	        return null;
	    }
	 
	  @Override
	    public UserDto findByUsername(String username) {
	        // Use the UserRepository to find the user by username
	        User user = userRepository.findByEmail(username);
	        
	      
	            return userToDto(user);
	      
	    }
	
	 @Override
		public UserDto createUser(UserDto userDto) {
			User user = this.dtoToUser(userDto);
			User savedUser = this.userRepository.save(user);
			return this.userToDto(savedUser);
		}

	

	 @Override
		public UserDto updateUser(UserDto userDto, Long userId) {

			User user = this.userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

			user.setFullname(userDto.getFullname());
			user.setEmail(userDto.getEmail());
			user.setPassword(userDto.getPassword());
			

			User updatedUser = this.userRepository.save(user);
			UserDto userDto1 = this.userToDto(updatedUser);
			return userDto1;
		}

		@Override
		public UserDto getUserById(Long userId) {

			User user = this.userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

			return this.userToDto(user);
		}

		@Override
		public List<UserDto> getAllUsers() {

			List<User> users = this.userRepository.findAll();
			List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

			return userDtos;
		}

		@Override
		public void deleteUser(Long userId) {
			User user = this.userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
			this.userRepository.delete(user);

		}


	
		@Override
		public User dtoToUser(UserDto userDto) {
			User user = this.modelMapper.map(userDto, User.class);

		
			return user;
		}

		public UserDto userToDto(User user) {
			UserDto userDto = this.modelMapper.map(user, UserDto.class);
			return userDto;
		}
	
}
