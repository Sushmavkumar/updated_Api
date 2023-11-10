package com.surabhichainrestaurant.foodorder.service;

import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

//
import com.surabhichainrestaurant.foodorder.entity.User;
import com.surabhichainrestaurant.foodorder.entity.UserLoginRequest;
import com.surabhichainrestaurant.foodorder.repo.UserRepo;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Service
public class UserService {

	// @Autowired
	// private UserRepo userRepository;
	//
	// @Autowired
	// private BCryptPasswordEncoder passwordEncoder;
	//
	// public void registerUser(User user) {
	// // Encode the user's password before saving it to the database
	// user.setPassword(passwordEncoder.encode(user.getPassword()));
	// userRepository.save(user);
	// }

	@Autowired
	UserRepo repo;

	public User addUser(User user) {
		return repo.save(user);

	}
	public User findUser(UserLoginRequest request) {
		
		User userValid=repo.findByEmailAndPassword(request.getEmail(),request.getPassword());
		if(userValid==null)
		{
			return null;
		}
		else
		{
			return userValid;
		}
	}

//		public void deleteUser(Long id) {
//			Optional<User> optionalUser = repo.findById(id);
//			if(optionalUser.isPresent()) {
//				User existingUser = optionalUser.get();
//				repo.delete(existingUser);
//				
//			}
//			else {
//				
//			}
//		}
	public void  deleteUser(Long id) {
		java.util.Optional<User> delete=repo.findById(id);
		if(delete!=null) {
			repo.deleteAll();
		}
		
		
	}
	
	
	public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
		User existingUser=repo.findById(id).orElse(null);
		if(existingUser!=null) {
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setPassword(updatedUser.getPassword());
			repo.save(existingUser);
			return existingUser;
		}
		else {
			return null;
		}
	}
		
	

}
