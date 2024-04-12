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
import com.project.exceptions.ResourceNotFoundException;
import com.project.repo.UserRepository;
import com.project.service.UserService;

import jakarta.transaction.Transactional;

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
		User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , userDto.getFullname(), userDto.getRole(),userDto.getPhone(),userDto.getLocation());
		user.setRole("USER");
		user.setPhone("null");
		user.setLocation("null");
		return userRepository.save(user);
	}
	
	 @Override
	    public UserDto getCurrentUser() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        if (authentication != null && authentication.isAuthenticated()) {
	            String userEmail = authentication.getName();
	            User user = userRepository.findByEmail(userEmail);
	            
	            if (user != null) {
	                return new UserDto(user.getUserId(),user.getEmail(), user.getFullname(),user.getPhone(),user.getLocation());
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
	    @Transactional()
	    public UserDto getUserById(Long userId) {
	        User user = userRepository.findById(userId).orElse(null);
	        if (user != null) {
	            return convertToDto(user);
	        }
	        return null;
	    }

	    @Override
	    @Transactional
	    public void updateUser(Long userId, UserDto userDto) {
	        User user = userRepository.findById(userId).orElse(null);
	        if (user != null) {
	            user.setEmail(userDto.getEmail());
	            user.setFullname(userDto.getFullname());
	            user.setPhone(userDto.getPhone());
	            user.setLocation(userDto.getLocation());
	            // Update other fields as needed
	            userRepository.save(user);
	        }
	    }

	    // Utility method to convert User entity to UserDto
	    private UserDto convertToDto(User user) {
	        return new UserDto(user.getUserId(), user.getEmail(), user.getFullname());
	    }
	
		@Override
		public User dtoToUser(UserDto userDto) {
			User user = this.modelMapper.map(userDto, User.class);

		
			return user;
		}

		@Override
		public UserDto userToDto(User user) {
			UserDto userDto = this.modelMapper.map(user, UserDto.class);
			return userDto;
		}
	
}
